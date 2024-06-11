package com.bookstore.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bookstore.entity.Cart;
import com.bookstore.entity.User;

@Repository
public interface CartDao extends JpaRepository<Cart, Integer> {
	
	public List<Cart> findByUser(User user);

}
