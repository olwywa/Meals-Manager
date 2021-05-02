package com.example.mealsmanagerapp.controllers;

import com.example.mealsmanagerapp.models.User;
import com.example.mealsmanagerapp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.login.AccountNotFoundException;
import java.util.Optional;

@RestController
@RequestMapping(path = "/user")
public class UserController {

//    @GetMapping("home")
//    public String home(){
//        return "user/home";
//    }

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping()
    public String createUser(@RequestBody User user) {

        userService.saveUser(user);
        return "redirect:/login.html";
    }

    @PutMapping(path = "/edit/{id}")
    public void updateUser(@PathVariable("id") Long id,
                           @RequestParam(required = false) String name,
                           @RequestParam(required = false) String email,
                           @RequestParam(required = false) String password) {
        userService.updateUser(id, name, email, password);
    }

    @DeleteMapping(path = "{id}")
    public void deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);
    }

    @GetMapping("/{id}")
    public User findUserById(@PathVariable("id") Long id) {
        return userService.findUserById(id);
    }
}