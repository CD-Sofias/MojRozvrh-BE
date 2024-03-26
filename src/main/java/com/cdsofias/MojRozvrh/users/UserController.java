package com.cdsofias.MojRozvrh.users;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("users")
@AllArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping
    public User createUser(@RequestBody CreateUserDto userDto) {
        return userService.createUser(userDto);
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
    public User updateUserById(@PathVariable UUID id, @RequestBody User user, @PathVariable(required = false) UUID departmentId) {
        return userService.updateUserById(id, user, departmentId);
    }
}
