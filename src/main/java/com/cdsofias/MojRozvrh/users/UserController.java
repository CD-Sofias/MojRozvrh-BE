package com.cdsofias.MojRozvrh.users;


import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("users")
@AllArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping
    public User createUser(@Valid @RequestBody CreateUserDto userDto) {
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
    public User updateUserById(@PathVariable UUID id, @Valid @RequestBody CreateUserDto userDto) {
        return userService.updateUserById(id, userDto);
    }

    @GetMapping("info")
    public ResponseEntity<User> getUserInfo(Principal principal) {
        return ResponseEntity.ok(userService.findByUsername(principal.getName()).orElse(null));
    }
}
