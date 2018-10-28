package ru.ibank.db.mapper.user;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.ibank.db.dao.user.UserDAOImpl;
import ru.ibank.db.dto.UserDTO;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("Тест: userService")
class UserDAOImplTest {

    private static ClassPathXmlApplicationContext ctx;
    private static UserDAOImpl userService;
    private static UserDTO user;

    @BeforeAll
    static void init() {
        ctx = new ClassPathXmlApplicationContext(new String[]{"META-INF/spring/applicationContext.xml", "META-INF/spring/beans.xml"});
        userService = ctx.getBean("userDAOImpl", UserDAOImpl.class);
        user = new UserDTO();
        user.setLastName("Иванов");
        user.setFirstName("Иван");
        user.setMiddleName("Иванович");
    }

    @Test()
    @DisplayName("Создание нового пользователя")
    void createUser() {
       Long id = userService.createUser(user);
       assertNotNull(id);
    }

    @Test
    @DisplayName("Поиск пользователя по id")
    void findUserById() {
        UserDTO findUser = userService.findUserById(user.getId());
        assertEquals(user, findUser);
    }

    @Test
    @DisplayName("Изменение пользователя")
    void updateUser() {
        user.setFirstName("Дмитрий");
        Boolean result = userService.updateUser(user);
    }

    @Test
    @DisplayName("Удаление пользователя")
    void deleteUser() {
        Boolean result = userService.deleteUser(user.getId());
        assertTrue(result);
    }

}