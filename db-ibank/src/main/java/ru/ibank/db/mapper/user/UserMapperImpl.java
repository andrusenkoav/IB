package ru.ibank.db.mapper.user;

import org.apache.ibatis.session.SqlSessionFactory;
import ru.ibank.db.bean.SqlSessionCloseable;


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
    public Long createUser(User user) {
        try (SqlSessionCloseable sqlSession = new SqlSessionCloseable(sqlSessionFactory)){
            userMapper = sqlSession.getMapper(UserMapper.class);
            userMapper.createUser(user);
            return user.getId();
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
    public void updateUser(User user) throws UserException {
        try (SqlSessionCloseable sqlSession = new SqlSessionCloseable(sqlSessionFactory)){
            userMapper = sqlSession.getMapper(UserMapper.class);
            userMapper.updateUser(user);
        } catch (Exception e) {
           throw new UserException("Ошибка обновление записи:" + e.getMessage());
        }
    }

    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }
}
