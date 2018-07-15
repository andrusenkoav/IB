package ru.ibank.model.user;


import ru.ibank.db.user.User;

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
    @WebResult (name = "response")
    User findUserById (@WebParam Long userId);

}
