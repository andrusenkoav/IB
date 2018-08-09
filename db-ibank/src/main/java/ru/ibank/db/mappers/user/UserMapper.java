package ru.ibank.db.mappers.user;

public interface UserMapper {

    User findUserById (long userId);

    long createUser (User user);

    void deleteUser (long userId);

    void updateUser (User user);
}
