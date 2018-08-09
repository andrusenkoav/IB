package ru.ibank.ws.user;

import org.apache.camel.Body;
import ru.ibank.db.mappers.user.User;
import ru.ibank.db.mappers.user.UserMapper;

import java.util.ArrayList;

public class UserService {

    private UserMapper userMapper;

    public User findUserById (@Body ArrayList params){
        Long userId = (Long) params.get(0);
        return userMapper.findUserById(userId);
    }

    public Long createUser (@Body User user){
        return userMapper.createUser(user);
    }

    public Boolean updateUser(@Body User user) {
        userMapper.updateUser(user);
        return true;
    }


    public Boolean deleteUser(@Body ArrayList params) {
        Long userId = (Long) params.get(0);
        userMapper.deleteUser(userId);
        return true;
    }


    public UserMapper getUserMapper() {
        return userMapper;
    }

    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }
}
