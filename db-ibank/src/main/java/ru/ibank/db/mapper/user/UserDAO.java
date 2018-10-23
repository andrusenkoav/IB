package ru.ibank.db.mapper.user;

public interface UserDAO {

    UserDTO findUserById (long userId);

    Long createUser (UserDTO user);

    void deleteUser (long userId);

    void updateUser (UserDTO user) throws UserException;
}
