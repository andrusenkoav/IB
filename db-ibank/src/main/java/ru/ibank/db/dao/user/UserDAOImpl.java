package ru.ibank.db.dao.user;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import ru.ibank.db.dto.UserDTO;


public class UserDAOImpl implements UserDAO {

    private SqlSessionFactory sqlSessionFactory;
    private UserDAO userMapper;

    @Override
    public UserDTO findUserById(long userId) {

        SqlSession sqlSession = sqlSessionFactory.openSession(true);

        try {
            userMapper = sqlSession.getMapper(UserDAO.class);
            return userMapper.findUserById(userId);
        } finally {
            sqlSession.close();
        }
    }

    @Override
    public Long createUser(UserDTO user) {

        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        try {
            userMapper = sqlSession.getMapper(UserDAO.class);
            userMapper.createUser(user);
            return user.getId();
        } finally {
            sqlSession.close();
        }
    }

    @Override
    public Boolean deleteUser(long userId) {

        SqlSession sqlSession = sqlSessionFactory.openSession(true);

        try {
            userMapper = sqlSession.getMapper(UserDAO.class);
            userMapper.deleteUser(userId);
            return true;
        } catch (Exception e) {
            return false;
        } finally {
            sqlSession.close();
        }
    }

    @Override
    public Boolean updateUser(UserDTO user) {

        SqlSession sqlSession = sqlSessionFactory.openSession(true);

        try {
            userMapper = sqlSession.getMapper(UserDAO.class);
            userMapper.updateUser(user);
            return true;
        } catch (Exception e) {
            return false;
        } finally {
            sqlSession.close();
        }
    }

    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }
}
