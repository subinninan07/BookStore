package com.bookstore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.bookstore.entity.Cart;
import com.bookstore.exception.ProductNotFoundException;
import com.bookstore.exception.UserNotFoundException;
import com.bookstore.serviceImpl.CartServiceImpl;


@RestController
public class CartController {

    @Autowired
    private CartServiceImpl cartServiceImpl;

    @PreAuthorize("hasRole('User')")
    @GetMapping({"/addToCart/{productId}"})
    public ResponseEntity<?> addToCart(@PathVariable(name="productId") Integer productId) {
        try {

            Cart cart = cartServiceImpl.addToCart(productId);
            if (cart == null) {
                // Handle duplicate cart item
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Product is already in the cart.");
            }
            return ResponseEntity.ok(cart);
        } catch (ProductNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found with ID: " + productId);
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("User not found.");
        }
    }

    @DeleteMapping({"/deleteCartItem/{cartId}"})
    public ResponseEntity<?> deleteCartItem(@PathVariable(name= "cartId") Integer cartId) {
    	
        try {

            cartServiceImpl.deleteCartItem(cartId);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to delete cart item.");
        }
    }

    @PreAuthorize("hasRole('User')")
    @GetMapping({"/getCartDetails"})
    public ResponseEntity<?> getCartDetails() {
        try {

            List<Cart> cartDetails = cartServiceImpl.getCartDetails();
            return ResponseEntity.ok(cartDetails);
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("User not found.");
        }
    }
}
