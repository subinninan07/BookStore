package com.bookstore.controller;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bookstore.entity.User;
import com.bookstore.exception.RegistrationException;
import com.bookstore.serviceImpl.UserServiceImpl;


@RestController
public class UserController {

    @Autowired
    private UserServiceImpl userServiceImpl;

    @PostConstruct
    public void initRoleAndUser() {
        userServiceImpl.initRoleAndUser();
    }

    @PostMapping("/registerNewUser")
    public User registerNewUser(@RequestBody User user) {
        try {

            return userServiceImpl.registerNewUser(user);
        } catch (RegistrationException e) {
            return null;
        }
    }

    @GetMapping("/forAdmin")
    @PreAuthorize("hasRole('Admin')")
    public String forAdmin() {
        try {

            return "This URL is only accessible to the admin";
        } catch (AccessDeniedException e) {
            return null;
        }
    }

    @GetMapping("/forUser")
    @PreAuthorize("hasRole('User')")
    public String forUser() {
        try {

            return "This URL is only accessible to the user";
        } catch (AccessDeniedException e) {
            return null;
        }
    }
}


