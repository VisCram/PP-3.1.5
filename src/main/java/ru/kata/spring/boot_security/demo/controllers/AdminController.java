package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.repository.UserRepository;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.security.Principal;
import java.util.List;


@Controller
@RequestMapping("/admin")
public class AdminController {
    private UserService userService;
    private UserRepository userRepository;
    private RoleService roleService;

    public AdminController(UserService userService, UserRepository userRepository, RoleService roleService) {
        this.userService = userService;
        this.userRepository = userRepository;
        this.roleService = roleService;
    }

    @GetMapping("/")
    public String showAllUsers(Model model, Principal principal) {
        List<User> allUser = userService.getAllUsers();
        User userAuth = userRepository.findByUsername(principal.getName());
        model.addAttribute("users", userAuth);
        model.addAttribute("allUser", allUser);
        model.addAttribute("allRole", roleService.getAllRoles());
        return "admin";
    }

    @GetMapping("/new")
    public String showNewUserForm(Model model, Principal principal) {
        User userAuth = userRepository.findByUsername(principal.getName());
        model.addAttribute("users", userAuth);
        User user = new User();
        model.addAttribute("user", user);
        model.addAttribute("allRole", roleService.getAllRoles());
        return "user_info";
    }

    @PostMapping("/save")
    public String saveUser(@ModelAttribute("user") User user) {
        userService.saveUser(user);
        return "redirect:/admin/";
    }

    @PutMapping("/updateInfo/{id}")
    public String updateUser(@ModelAttribute("user") User user) {
        System.out.println(user.toString());
        userService.saveUser(user);
        return "redirect:/admin/";
    }

    @DeleteMapping("/deleteInfo")
    public String deleteUser(@RequestParam(value = "id") Long id) {
        userService.deleteUserById(id);
        return "redirect:/admin/";
    }
}

