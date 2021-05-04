package com.example.mealsmanagerapp.controllers;

import com.example.mealsmanagerapp.models.User;
import com.example.mealsmanagerapp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.security.auth.login.AccountNotFoundException;
import java.util.Optional;

@RestController
@RequestMapping(path = "/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PutMapping(path = "/edit/{id}")
    public void updateUser(@PathVariable("id") Long id,
                           @RequestParam(required = false) String name,
                           @RequestParam(required = false) String email,
                           @RequestParam(required = false) String password) {
        userService.updateUser(id, name, email, password);
    }

    @DeleteMapping(path = "/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);
        return "redirect:/deletedAccount";
    }

    @GetMapping("/{id}")
    public User findUserById(@PathVariable("id") Long id) {
        return userService.findUserById(id);
    }
}