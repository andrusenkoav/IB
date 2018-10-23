package ru.ibank.db.mapper.user;

import org.apache.ibatis.session.SqlSessionFactory;
import ru.ibank.db.bean.SqlSessionCloseable;


public class UserDAOImpl implements UserDAO {

    private SqlSessionFactory sqlSessionFactory;
    private UserDAO userMapper;

    @Override
    public UserDTO findUserById(long userId) {
        try (SqlSessionCloseable sqlSession = new SqlSessionCloseable(sqlSessionFactory)){
            userMapper = sqlSession.getMapper(UserDAO.class);
            return userMapper.findUserById(userId);
        }
    }

    @Override
    public Long createUser(UserDTO user) {
        try (SqlSessionCloseable sqlSession = new SqlSessionCloseable(sqlSessionFactory)){
            userMapper = sqlSession.getMapper(UserDAO.class);
            userMapper.createUser(user);
            return user.getId();
        }
    }

    @Override
    public Boolean deleteUser(long userId) {
        try (SqlSessionCloseable sqlSession = new SqlSessionCloseable(sqlSessionFactory)){
            userMapper = sqlSession.getMapper(UserDAO.class);
            userMapper.deleteUser(userId);
            return true;
        } catch (Exception e){
            return false;
        }
    }

    @Override
    public Boolean updateUser(UserDTO user) {
        try (SqlSessionCloseable sqlSession = new SqlSessionCloseable(sqlSessionFactory)){
            userMapper = sqlSession.getMapper(UserDAO.class);
            userMapper.updateUser(user);
            return true;
        } catch (Exception e) {
           return false;
        }
    }

    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }
}
