package com.cdsofias.MojRozvrh.users;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public User createUser(User user) {
        user.setId(UUID.randomUUID());
        return userRepository.save(user);
    }

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User findUserById(UUID id) {
        Optional<User> user = userRepository.findById(id);
        return user.orElse(null);
    }

    @Override
    public User deleteUserById(UUID id) {
        userRepository.deleteById(id);
        return null;
    }

    @Override
    public User updateUserById(UUID id, User newUser) {
        Optional<User> optionalUser = userRepository.findById(id);
        if(optionalUser.isPresent()){
            User user = optionalUser.get();
            user.setName(newUser.getName());
            user.setSurname(newUser.getSurname());
            user.setEmail(newUser.getEmail());
            user.setPassword(newUser.getPassword());
            user.setRole(newUser.getRole());
            return userRepository.save(user);
        }
        return null;
    }
}
