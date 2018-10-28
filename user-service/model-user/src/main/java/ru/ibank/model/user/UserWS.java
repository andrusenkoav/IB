package ru.ibank.model.user;

import ru.ibank.db.dto.UserDTO;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

@WebService
public interface UserWS {
    @WebMethod
    @WebResult (name = "result")
    Long addUser (@WebParam(name = "user") UserDTO user);

    @WebMethod
    @WebResult (name = "user")
    UserDTO findUserById (@WebParam (name = "userId") Long userId);

    @WebMethod
    @WebResult (name = "result")
    Boolean updateUser (@WebParam(name = "user") UserDTO user);

    @WebMethod
    @WebResult (name = "result")
    Boolean deleteUser (@WebParam(name = "userId") Long id);

}
