package ru.ibank.model.user;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

@WebService
public interface UserWS {
    @WebMethod
    @WebResult (name = "result")
    Long addUser (@WebParam(name = "user") User user);

    @WebMethod
    @WebResult (name = "user")
    User findUserById (@WebParam (name = "userId") Long userId);

    @WebMethod
    @WebResult (name = "result")
    Boolean updateUser (@WebParam(name = "user") User user);

    @WebMethod
    @WebResult (name = "result")
    Boolean deleteUser (@WebParam(name = "userId") Long id);

}
