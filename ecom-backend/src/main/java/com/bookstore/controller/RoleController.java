package com.bookstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bookstore.entity.Role;
import com.bookstore.exception.RoleCreationException;
import com.bookstore.serviceImpl.RoleServiceImpl;


@RestController
public class RoleController {

    @Autowired
    private RoleServiceImpl roleServiceImpl;

    @PostMapping({"/createNewRole"})
    public ResponseEntity<?> createNewRole(@RequestBody Role role) {
        try {

            Role createdRole = roleServiceImpl.createNewRole(role);
            return ResponseEntity.ok(createdRole);
        } catch (RoleCreationException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to create new role: " + e.getMessage());
        }
    }
}
