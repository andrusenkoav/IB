package ru.ibank.db.user;

import org.apache.ibatis.session.SqlSessionFactory;
import ru.ibank.db.beans.SqlSessionCloseable;


public class UserMapperImpl implements UserMapper {

    private SqlSessionFactory sqlSessionFactory;
    private UserMapper userMapper;

    public User findUserById(long userId) {
        try (SqlSessionCloseable sqlSession = new SqlSessionCloseable(sqlSessionFactory)){
            userMapper = sqlSession.getMapper(UserMapper.class);
            return userMapper.findUserById(userId);
        }
    }

    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }
}
