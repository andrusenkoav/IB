package ru.ibank.db;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.ibank.db.mappers.user.User;
import ru.ibank.db.mappers.user.UserMapper;
import ru.ibank.db.mappers.user.UserMapperImpl;

public class TestUserMapper {

    private static ClassPathXmlApplicationContext ctx;

    @BeforeClass
    public static void init() {
        ctx = new ClassPathXmlApplicationContext(new String[]{"META-INF/spring/applicationContext.xml", "META-INF/spring/beans.xml"});
    }

    @Test
    public void findUserById () {
        UserMapper userMapper = ctx.getBean("userMapperImpl", UserMapperImpl.class);
        User user = userMapper.findUserById(2);
        Assert.assertTrue("Фамилия не совпадает", (user.getLastName().equalsIgnoreCase("Андрусенко")));
        System.out.println(user);
    }
}
