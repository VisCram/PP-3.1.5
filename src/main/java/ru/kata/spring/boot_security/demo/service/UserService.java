package ru.kata.spring.boot_security.demo.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

public interface UserService extends UserDetailsService {
    User findUserById(long id);

    List<User> allUsers();

    void addUser(User user);

    void updateUser(User user);

    void deleteUser(Long id);

    User getUserByUsername(String username);
    UserDetails loadUserByUsername(String username);

    void saveUser(User user);

}