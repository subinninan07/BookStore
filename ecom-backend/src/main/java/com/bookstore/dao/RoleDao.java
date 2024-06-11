package com.bookstore.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bookstore.entity.Role;

@Repository
public interface RoleDao extends JpaRepository<Role, String> {

}
