package com.cdsofias.MojRozvrh.users;


import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("users")
@AllArgsConstructor
public class UserController {
    private final UserServiceImpl userService;

    @PostMapping
    public ResponseEntity<User> createUser(@Valid @RequestBody CreateUserDto userDto) {
        User user = userService.createUser(userDto);
        return ResponseEntity.created(URI.create("/user/" + user.getId())).body(user);
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
