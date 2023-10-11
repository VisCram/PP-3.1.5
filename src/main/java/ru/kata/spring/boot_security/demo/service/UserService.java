package ru.kata.spring.boot_security.demo.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

public interface UserService extends UserDetailsService {
    User findByUsername(String name);

    User showUser(Long id);

    void deleteUserById(Long id);

    List<User> getAllUsers();

    void updateUser(long id, User user);

    void saveUser(User user);

}