package ru.ibank.db.user;

import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import ru.ibank.db.beans.SqlSessionClosable;


public class UserMapperImpl implements UserMapper {

    private SqlSessionFactory sqlSessionFactory;
    private UserMapper userMapper;

    public User findUserById(long userId) {
        try (SqlSessionClosable sqlSession = new SqlSessionClosable(sqlSessionFactory)){
            userMapper = sqlSession.getMapper(UserMapper.class);
            return userMapper.findUserById(userId);
        }
    }

    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }
}
