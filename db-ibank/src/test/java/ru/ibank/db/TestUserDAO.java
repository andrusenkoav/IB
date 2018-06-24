package ru.ibank.db;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import ru.ibank.db.user.User;
import ru.ibank.db.user.UserDAO;

import java.io.IOException;
import java.io.Reader;

public class TestUserDAO {
    public static void main(String[] args) {
        SqlSessionFactory sqlSessionFactory;
        UserDAO userDAO;
        Reader reader = null;
        try {
            //Читаем файл с настройками подключения и настройками MyBatis
            reader = Resources.getResourceAsReader("META-INF/db-config.xml");
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
            userDAO = sqlSessionFactory.openSession().getMapper(UserDAO.class);

            //Создаем маппер, из которого и будем вызывать методы
            User user = userDAO.findUserById(2);

            System.out.println(user);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
