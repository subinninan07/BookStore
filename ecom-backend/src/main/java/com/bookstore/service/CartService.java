package com.bookstore.service;

import com.bookstore.entity.Cart;

import java.util.List;

public interface CartService {
    void deleteCartItem(Integer cartId);
    Cart addToCart(Integer productId);
    List<Cart> getCartDetails();
}
