package ru.ibank.db.mapper.user;

public interface UserMapper {

    User findUserById (long userId);

    Long createUser (User user);

    void deleteUser (long userId);

    void updateUser (User user) throws UserException;
}
