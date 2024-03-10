package com.cdsofias.MojRozvrh.users;


import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/users")
@AllArgsConstructor
public class UserController {
    private final UserServiceImpl userService;

    @PostMapping
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    @GetMapping
    public List<User> findAllUsers() {
        return userService.findAllUsers();
    }

    @GetMapping("{id}")
    public User findUserById(@PathVariable UUID id) {
        return userService.findUserById(id);
    }

    @DeleteMapping("{id}")
    public User deleteUserById(@PathVariable UUID id) {
        return userService.deleteUserById(id);
    }

    @PutMapping("{id}")
    public User updateUserById(@PathVariable UUID id, @RequestBody User user) {
        return userService.updateUserById(id, user);
    }
}
