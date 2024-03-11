package com.cdsofias.MojRozvrh;

import com.cdsofias.MojRozvrh.users.Role;
import com.cdsofias.MojRozvrh.users.User;
import com.cdsofias.MojRozvrh.users.UserServiceImpl;
import com.cdsofias.MojRozvrh.department.Department;
import com.cdsofias.MojRozvrh.department.DepartmentRepository;
import com.cdsofias.MojRozvrh.users.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class MojRozvrhApplicationUserTests {

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private DepartmentRepository departmentRepository;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCreateUser() {
        User user = new User();
        user.setEmail("test@test.com");
        Department department = new Department();
        department.setId(UUID.randomUUID());
        user.setDepartment(department);

        when(userRepository.findByEmail(any(String.class))).thenReturn(Optional.empty());
        when(departmentRepository.findById(any(UUID.class))).thenReturn(Optional.of(department));
        when(userRepository.saveAndFlush(any(User.class))).thenReturn(user);

        User createdUser = userService.createUser(user);

        assertEquals(user.getEmail(), createdUser.getEmail());
        assertEquals(user.getDepartment().getId(), createdUser.getDepartment().getId());
    }

    @Test
    public void testFindAllUsers() {
        User user1 = new User();
        user1.setEmail("test1@test.com");
        Department department1 = new Department();
        department1.setId(UUID.randomUUID());
        user1.setDepartment(department1);

        User user2 = new User();
        user2.setEmail("test2@test.com");
        Department department2 = new Department();
        department2.setId(UUID.randomUUID());
        user2.setDepartment(department2);

        List<User> users = Arrays.asList(user1, user2);

        when(userRepository.findAll()).thenReturn(users);

        List<User> returnedUsers = userService.findAllUsers();

        assertEquals(2, returnedUsers.size());
        assertEquals(user1.getEmail(), returnedUsers.get(0).getEmail());
        assertEquals(user2.getEmail(), returnedUsers.get(1).getEmail());
    }


    @Test
    public void testFindUserById() {
        User user = new User();
        user.setEmail("test@test.com");
        Department department = new Department();
        department.setId(UUID.randomUUID());
        user.setDepartment(department);

        UUID userId = UUID.randomUUID();
        user.setId(userId);

        when(userRepository.findById(any(UUID.class))).thenReturn(Optional.of(user));

        User returnedUser = userService.findUserById(userId);

        assertEquals(user.getId(), returnedUser.getId());
        assertEquals(user.getEmail(), returnedUser.getEmail());
        assertEquals(user.getDepartment().getId(), returnedUser.getDepartment().getId());
    }

    @Test
    public void testDeleteUserById() {
        UUID userId = UUID.randomUUID();

        when(userRepository.existsById(any(UUID.class))).thenReturn(true);

        userService.deleteUserById(userId);

        verify(userRepository, times(1)).deleteById(userId);
    }

    @Test
    public void testDeleteUserByIdThrowsException() {
        UUID userId = UUID.randomUUID();

        when(userRepository.existsById(any(UUID.class))).thenReturn(false);

        assertThrows(IllegalStateException.class, () -> userService.deleteUserById(userId));

        verify(userRepository, times(0)).deleteById(any(UUID.class));
    }

    @Test
    public void testUpdateUserById() {
        UUID userId = UUID.randomUUID();
        User existingUser = new User();
        existingUser.setId(userId);
        existingUser.setEmail("test@test.com");
        existingUser.setName("Test");
        existingUser.setSurname("User");
        existingUser.setPassword("password");
        existingUser.setRole(Role.USER);

        Department department = new Department();
        department.setId(UUID.randomUUID());
        existingUser.setDepartment(department);

        User newUser = new User();
        newUser.setName("NewTest");
        newUser.setSurname("NewUser");
        newUser.setEmail("newtest@newtest.com");
        newUser.setPassword("newpassword");
        newUser.setRole(Role.ADMIN);

        Department newDepartment = new Department();
        newDepartment.setId(UUID.randomUUID());
        newUser.setDepartment(newDepartment);

        when(userRepository.findById(any(UUID.class))).thenReturn(Optional.of(existingUser));
        when(departmentRepository.findById(any(UUID.class))).thenReturn(Optional.of(newDepartment));
        when(userRepository.save(any(User.class))).thenReturn(newUser);

        User updatedUser = userService.updateUserById(userId, newUser, newDepartment.getId());

        assertEquals(newUser.getName(), updatedUser.getName());
        assertEquals(newUser.getSurname(), updatedUser.getSurname());
        assertEquals(newUser.getEmail(), updatedUser.getEmail());
        assertEquals(newUser.getPassword(), updatedUser.getPassword());
        assertEquals(newUser.getRole(), updatedUser.getRole());
        assertEquals(newDepartment.getId(), updatedUser.getDepartment().getId());
    }
}
