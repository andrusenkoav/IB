package ru.ibank.db.mappers.user;

import org.apache.ibatis.session.SqlSessionFactory;
import ru.ibank.db.beans.SqlSessionCloseable;


public class UserMapperImpl implements UserMapper {

    private SqlSessionFactory sqlSessionFactory;
    private UserMapper userMapper;

    @Override
    public User findUserById(long userId) {
        try (SqlSessionCloseable sqlSession = new SqlSessionCloseable(sqlSessionFactory)){
            userMapper = sqlSession.getMapper(UserMapper.class);
            return userMapper.findUserById(userId);
        }
    }

    @Override
    public long createUser(User user) {
        try (SqlSessionCloseable sqlSession = new SqlSessionCloseable(sqlSessionFactory)){
            userMapper = sqlSession.getMapper(UserMapper.class);
            return userMapper.createUser(user);
        }
    }

    @Override
    public void deleteUser(long userId) {
        try (SqlSessionCloseable sqlSession = new SqlSessionCloseable(sqlSessionFactory)){
            userMapper = sqlSession.getMapper(UserMapper.class);
            userMapper.deleteUser(userId);
        }
    }

    @Override
    public void updateUser(User user) {
        try (SqlSessionCloseable sqlSession = new SqlSessionCloseable(sqlSessionFactory)){
            userMapper = sqlSession.getMapper(UserMapper.class);
            userMapper.updateUser(user);
        }
    }

    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }
}
