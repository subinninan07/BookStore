
package com.bookstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bookstore.entity.JwtRequest;
import com.bookstore.entity.JwtResponse;
import com.bookstore.exception.InvalidCredentialsException;
import com.bookstore.serviceImpl.JwtServiceImpl;


@RestController
@CrossOrigin
public class JwtController {

    @Autowired
    private JwtServiceImpl jwtServiceImpl;

    @PostMapping({"/authenticate"})
    public ResponseEntity<?> createJwtToken(@RequestBody JwtRequest jwtRequest) {
        try {

            JwtResponse jwtResponse = jwtServiceImpl.createJwtToken(jwtRequest);
            return ResponseEntity.ok(jwtResponse);
        } catch (InvalidCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal server error.");
        }
    }
}
