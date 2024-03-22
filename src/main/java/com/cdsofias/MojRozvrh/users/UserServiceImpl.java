package com.cdsofias.MojRozvrh.users;

import com.cdsofias.MojRozvrh.department.Department;
import com.cdsofias.MojRozvrh.department.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
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
        public User createUser(User user) {
            Optional<User> userOptional = userRepository.findByEmail(user.getEmail());
            if (userOptional.isPresent()) {
                throw new IllegalStateException("User already exists");
            }

            if (user.getDepartment() != null && user.getDepartment().getId() != null) {
                Department department = departmentRepository.findById(user.getDepartment().getId())
                        .orElseThrow(() -> new RuntimeException("Department not found"));
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

//            scheduleRepository.deleteAll(user.getSchedules().remove());

            user.setDepartment(null);

            userRepository.deleteById(id);
            return null;
        }

        @Override
        public User updateUserById(UUID id, User newUser, UUID departmentId) {
            User user = userRepository.findById(id)
                    .orElseThrow(() -> new IllegalStateException(
                            "User with id " + id + " does not exist"));

            if (newUser.getName() != null && !newUser.getName().isEmpty() && !Objects.equals(user.getName(), newUser.getName())) {
                user.setName(newUser.getName());
            }

            if (newUser.getSurname() != null && !newUser.getSurname().isEmpty() && !Objects.equals(user.getSurname(), newUser.getSurname())) {
                user.setSurname(newUser.getSurname());
            }

            if (newUser.getEmail() != null && !newUser.getEmail().isEmpty() && !Objects.equals(user.getEmail(), newUser.getEmail())) {
                user.setEmail(newUser.getEmail());
            }

            if (newUser.getPassword() != null && !newUser.getPassword().isEmpty() && !Objects.equals(user.getPassword(), newUser.getPassword())) {
                user.setPassword(newUser.getPassword());
            }

            if (newUser.getRole() != null && !Objects.equals(user.getRole(), newUser.getRole())) {
                user.setRole(newUser.getRole());
            }

            if (departmentId != null) {
                Department department = departmentRepository.findById(departmentId)
                        .orElseThrow(() -> new IllegalStateException(
                                "Department with id " + departmentId + " does not exist"));
                user.setDepartment(department);
            }

            return userRepository.save(user);
        }

    }

