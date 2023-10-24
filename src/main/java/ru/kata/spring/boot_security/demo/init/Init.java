package ru.kata.spring.boot_security.demo.init;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.repository.RoleRepository;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;
import ru.kata.spring.boot_security.demo.service.UserServiceImpl;

import javax.annotation.PostConstruct;
import java.util.*;

@Component
public class Init {
    private UserService userService;
    private RoleRepository roleRepository;

    public Init(UserService userService, RoleRepository roleRepository) {
        this.userService = userService;
        this.roleRepository = roleRepository;
    }

    @PostConstruct
    public void init() {
        if (roleRepository.count() == 0) {
            Role role = new Role("ROLE_USER");
            roleRepository.save(role);
            Role role1 = new Role("ROLE_ADMIN");
            roleRepository.save(role1);

            User user = new User("user", "user", 20, "user", "user@mail.ru",
                    "user", List.of(role));
            userService.saveUser(user);

            User user1 = new User("admin", "admin", 20, "admin", "admin@mail.ru",
                    "admin", List.of(role, role1));
            userService.saveUser(user1);
        }
    }

}
