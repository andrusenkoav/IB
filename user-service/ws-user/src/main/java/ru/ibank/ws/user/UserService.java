package ru.ibank.ws.user;

import org.apache.camel.Body;
import ru.ibank.db.user.User;
import ru.ibank.model.user.UserRequest;

public class UserService {


    void create (@Body UserRequest userRequest){

    }

    User update(@Body UserRequest userRequest) {
        return null;
    }
}
