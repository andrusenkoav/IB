package ru.ibank.db;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.ibank.db.mapper.user.UserDTO;
import ru.ibank.db.mapper.user.UserDAO;
import ru.ibank.db.mapper.user.UserDAOImpl;

public class TestUserMapper {

    private static ClassPathXmlApplicationContext ctx;

    @BeforeClass
    public static void init() {
        ctx = new ClassPathXmlApplicationContext(new String[]{"META-INF/spring/applicationContext.xml", "META-INF/spring/beans.xml"});
    }

    @Test
    public void findUserById () {
        UserDAO userMapper = ctx.getBean("userMapperImpl", UserDAOImpl.class);
        UserDTO user = userMapper.findUserById(2);
        Assert.assertTrue("Фамилия не совпадает", (user.getLastName().equalsIgnoreCase("Андрусенко")));
        System.out.println(user);
    }
}
