package ru.ibank.ws.user;

import org.apache.camel.Body;
import ru.ibank.db.mappers.user.User;
import ru.ibank.db.mappers.user.UserMapper;
import ru.ibank.model.user.UserRequest;

import java.util.ArrayList;

public class UserService {

    private UserMapper userMapper;

    void create (@Body UserRequest userRequest){

    }

    public User update(@Body UserRequest userRequest) {
        return null;
    }

    public User findUserById (@Body ArrayList params){
        Long userId = (Long) params.get(0);
        return userMapper.findUserById(userId);
    }

    public UserMapper getUserMapper() {
        return userMapper;
    }

    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }
}
