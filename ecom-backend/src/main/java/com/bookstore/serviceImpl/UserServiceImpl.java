package com.bookstore.serviceImpl;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.bookstore.dao.RoleDao;
import com.bookstore.dao.UserDao;
import com.bookstore.entity.Role;
import com.bookstore.entity.User;
import com.bookstore.exception.RegistrationException;
import com.bookstore.exception.UserNotFoundException;

@Service
public class UserServiceImpl {

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void initRoleAndUser() {
    	
   	        Role adminRole = new Role();
   	        adminRole.setRoleName("Admin");
    	        adminRole.setRoleDescription("Admin role");
   	        roleDao.save(adminRole);
    	
    	        Role userRole = new Role();
   	        userRole.setRoleName("User");
   	        userRole.setRoleDescription("Default role for newly created record");
  	        roleDao.save(userRole);
    
   	        User adminUser = new User();
			adminUser.setUserName("subinninan");
			adminUser.setUserPassword(getEncodedPassword("12"));
			adminUser.setUserFirstName("Subin");
			adminUser.setUserLastName("Ninan");
   	        Set<Role> adminRoles = new HashSet<>();
   	        adminRoles.add(adminRole);
   	        adminUser.setRole(adminRoles);
   	        userDao.save(adminUser);
    	    }

    public User registerNewUser(User user) {
        try {
            Role role = roleDao.findById("User").orElseThrow(() -> new UserNotFoundException("User not found."));
            Set<Role> userRoles = new HashSet<>();
            userRoles.add(role);
            user.setRole(userRoles);
            user.setUserPassword(getEncodedPassword(user.getUserPassword()));
            return userDao.save(user);
        } catch (Exception e) {
            throw new RegistrationException("Failed to register user: " + e.getMessage());
        }
    }

    public String getEncodedPassword(String password) {
        return passwordEncoder.encode(password);
    }
}
