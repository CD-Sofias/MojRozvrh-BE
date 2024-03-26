package com.cdsofias.MojRozvrh.users;

import java.util.List;
import java.util.UUID;

public interface UserService {
    User createUser(CreateUserDto userDto);
    List<User> findAllUsers();
    User findUserById(UUID id);
    User deleteUserById(UUID id);
    User updateUserById(UUID id, User newUser, UUID departmentId);
}
