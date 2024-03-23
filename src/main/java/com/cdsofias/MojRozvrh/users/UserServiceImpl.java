package com.cdsofias.MojRozvrh.users;

import com.cdsofias.MojRozvrh.department.Department;
import com.cdsofias.MojRozvrh.department.DepartmentRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

    @Service
    @RequiredArgsConstructor
    public class UserServiceImpl implements UserService {
        private final UserRepository userRepository;
        private final DepartmentRepository departmentRepository;

        @Override
        public List<User> findAllUsers() {
            return userRepository.findAll();
        }

        @Override
        public User createUser(CreateUserDto userDto) {
            User user = User.builder()
                    .username(userDto.username())
                    .email(userDto.email())
                    .password(userDto.password())
                    .role(userDto.role())
                    .build();
            if (userDto.departmentId() != null) {
                Department department = departmentRepository.findById(userDto.departmentId())
                        .orElseThrow(() -> new IllegalStateException(
                                "Department with id " + userDto.departmentId() + " does not exist"));
                user.setDepartment(department);
            }
            return userRepository.saveAndFlush(user);
        }



        @Override
        public User findUserById(UUID id) {
            Optional<User> user = userRepository.findById(id);
            return user.orElse(null);
        }
        @Override
        @Transactional
        public User deleteUserById(UUID id) {
            User user = userRepository.findById(id)
                    .orElseThrow(() -> new IllegalStateException(
                            "User with id " + id + " does not exist"));
            user.setDepartment(null);

            userRepository.deleteById(id);
            return null;
        }

        @Override
        public User updateUserById(UUID id, CreateUserDto userDto) {
            User user = userRepository.findById(id)
                    .orElseThrow(() -> new IllegalStateException(
                            "User with id " + id + " does not exist"));
            user.setUsername(userDto.username());
            user.setEmail(userDto.email());
            user.setPassword(userDto.password());
            user.setRole(userDto.role());
            if (userDto.departmentId() != null) {
                Department department = departmentRepository.findById(userDto.departmentId())
                        .orElseThrow(() -> new IllegalStateException(
                                "Department with id " + userDto.departmentId() + " does not exist"));
                user.setDepartment(department);
            }
            return userRepository.saveAndFlush(user);
        }

        @Override
        public Optional<User> findByUsername(String username) {
            return userRepository.findByUsername(username);
        }

        @Override
        public Optional<User> findByEmail(String email) {
            return userRepository.findByEmail(email);
        }

        @Override
        @Transactional
        public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
            User user = userRepository.findByUsername(username)
                    .orElseThrow(() -> new UsernameNotFoundException("User not found"));
            return new org.springframework.security.core.userdetails.User(
                    user.getUsername(),
                    user.getPassword(),
                    List.of(new SimpleGrantedAuthority("ROLE_USER"))
            );
        }
    }

