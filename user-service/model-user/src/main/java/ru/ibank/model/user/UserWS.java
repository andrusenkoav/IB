package ru.ibank.model.user;

import ru.ibank.model.user.UserCreateResponse;
import ru.ibank.model.user.UserRequest;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

@WebService
public interface UserWS {
    @WebMethod
    @WebResult (name = "response")
    UserCreateResponse create (@WebParam UserRequest userRequest);
}
