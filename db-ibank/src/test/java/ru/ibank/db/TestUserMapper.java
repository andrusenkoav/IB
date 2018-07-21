package ru.ibank.db;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.ibank.db.mappers.user.User;
import ru.ibank.db.mappers.user.UserMapper;
import ru.ibank.db.mappers.user.UserMapperImpl;

public class TestUserMapper {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("META-INF/spring/applicationContext.xml");
        UserMapper userMapper = ctx.getBean("userMapperImpl", UserMapperImpl.class);

        User user = userMapper.findUserById(2);

        System.out.println(user);
    }
}
