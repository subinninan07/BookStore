package com.bookstore.service;

import com.bookstore.entity.OrderDetail;
import com.bookstore.entity.OrderInput;

import java.util.List;

public interface OrderDetailService {
    List<OrderDetail> getAllOrderDetails();
    List<OrderDetail> getOrderDetails();
    void placeOrder(OrderInput orderInput, boolean isSingleProductCheckout);
}
