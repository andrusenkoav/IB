package ru.ibank.db.dao.user;

import ru.ibank.db.dto.UserDTO;

public interface UserDAO {

    UserDTO findUserById (long userId);

    Long createUser (UserDTO user);

    Boolean deleteUser (long userId);

    Boolean updateUser (UserDTO user);
}
