package com.bookstore.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bookstore.entity.OrderDetail;
import com.bookstore.entity.User;

@Repository
public interface OrderDetailDao extends JpaRepository<OrderDetail, Integer> {

	
	public List<OrderDetail> findByUser(User user);

}
