package com.bookstore.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bookstore.entity.User;

@Repository
public interface UserDao extends JpaRepository<User, String> {
}
