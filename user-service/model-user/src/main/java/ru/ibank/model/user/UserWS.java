package ru.ibank.model.user;


import ru.ibank.db.mappers.user.User;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

@WebService
public interface UserWS {
    @WebMethod
    @WebResult (name = "response")
    UserCreateResponse create (@WebParam UserRequest userRequest);

    @WebMethod
    @WebResult (name = "user")
    User findUserById (@WebParam (name = "userId") Long userId);

}
