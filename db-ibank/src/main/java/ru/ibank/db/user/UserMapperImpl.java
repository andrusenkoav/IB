package ru.ibank.db.user;

import org.apache.ibatis.session.SqlSessionFactory;

public class UserMapperImpl implements UserMapper {

    private SqlSessionFactory sqlSessionFactory;

    private UserMapper userMapper;

    public User findUserById(long userId) {
        return userMapper.findUserById(userId);
    }

    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
        userMapper = sqlSessionFactory.openSession().getMapper(UserMapper.class);
    }
}
