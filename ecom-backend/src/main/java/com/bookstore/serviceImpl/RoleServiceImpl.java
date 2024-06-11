package com.bookstore.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import com.bookstore.dao.RoleDao;
import com.bookstore.entity.Role;
import com.bookstore.exception.RoleCreationException;

@Service
public class RoleServiceImpl {

    @Autowired
    private RoleDao roleDao;

    public Role createNewRole(Role role) {
        try {
            return roleDao.save(role);
        } catch (Exception e) {
            throw new RoleCreationException("Failed to create new role: " + e.getMessage());
        }
    }
}
