package ru.ibank.db.mapper.user;

public interface UserDAO {

    UserDTO findUserById (long userId);

    Long createUser (UserDTO user);

    Boolean deleteUser (long userId);

    Boolean updateUser (UserDTO user);
}
