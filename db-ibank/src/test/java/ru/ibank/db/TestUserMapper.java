package ru.ibank.db;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.ibank.db.user.User;
import ru.ibank.db.user.UserMapperImpl;

public class TestUserMapper {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("META-INF/spring/beans.xml");
        UserMapperImpl userMapper = (UserMapperImpl) ctx.getBean("userMapper");

        User user = userMapper.findUserById(2);

        System.out.println(user);
    }
}
