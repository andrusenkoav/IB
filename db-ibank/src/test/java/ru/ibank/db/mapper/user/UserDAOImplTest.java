package ru.ibank.db.mapper.user;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@FixMethodOrder(MethodSorters.JVM)
public class UserDAOImplTest {

    private static ClassPathXmlApplicationContext ctx;
    private static UserDAO userService;
    private static UserDTO user;

    @BeforeClass
    public static void init() {
        ctx = new ClassPathXmlApplicationContext(new String[]{"META-INF/spring/applicationContext.xml", "META-INF/spring/beans.xml"});
        userService = ctx.getBean("userDAOImpl", UserDAOImpl.class);

        user = new UserDTO();
        user.setFirstName("Иван");
        user.setLastName("Иванов");
        user.setMiddleName("Иванович");
    }

    @Test
    public void createUser() {
        Long id = userService.createUser(user);
        Assert.assertNotNull(id);
        user.setId(id);
    }

    @Test
    public void findUserById() {
        UserDTO userFound = userService.findUserById(user.getId());
        Assert.assertEquals("Пользователи не совпадает", user, userFound);
        System.out.println(user);
    }

    @Test
    public void deleteUser() {
        Boolean deleted = userService.deleteUser(user.getId());
        Assert.assertTrue(deleted);
    }

    @Test
    public void updateUser() {
    }
}