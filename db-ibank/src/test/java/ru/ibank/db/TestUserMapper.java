package ru.ibank.db;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.ibank.db.user.User;
import ru.ibank.db.user.UserMapper;
import ru.ibank.db.user.UserMapperImpl;

public class TestUserMapper {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext(new String[]{"META-INF/spring/beans.xml", "META-INF/spring/applicationContext.xml"});
        UserMapper userMapper = ctx.getBean("userMapperImpl", UserMapperImpl.class);

        User user = userMapper.findUserById(2);

        System.out.println(user);
    }
}
