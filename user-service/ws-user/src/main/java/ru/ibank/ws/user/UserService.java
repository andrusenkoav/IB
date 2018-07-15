package ru.ibank.ws.user;

import org.apache.camel.Body;
import ru.ibank.db.user.User;
import ru.ibank.db.user.UserMapper;
import ru.ibank.model.user.UserRequest;

public class UserService {

    private UserMapper userMapper;

    void create (@Body UserRequest userRequest){

    }

    public User update(@Body UserRequest userRequest) {
        return null;
    }

    public User findUserById (@Body long userId){
        return userMapper.findUserById(userId);
    }

    public UserMapper getUserMapper() {
        return userMapper;
    }

    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }
}
