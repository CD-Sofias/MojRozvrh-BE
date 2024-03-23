package com.cdsofias.MojRozvrh.users;

import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserService extends UserDetailsService {
    User createUser(CreateUserDto userDto);
    List<User> findAllUsers();
    User findUserById(UUID id);
    User deleteUserById(UUID id);

    User updateUserById(UUID id, CreateUserDto userDto);

    Optional<User> findByUsername(String username);

    Optional<User> findByEmail(String email);

}
