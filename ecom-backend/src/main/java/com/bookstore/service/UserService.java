package com.bookstore.service;

import com.bookstore.entity.User;

public interface UserService {
    void initRoleAndUser();
    User registerNewUser(User user);
    String getEncodedPassword(String password);
}