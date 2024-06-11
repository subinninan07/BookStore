package com.bookstore.service;

import com.bookstore.entity.Product;

import java.util.List;

public interface ProductService {
    Product addNewProduct(Product product);
    List<Product> getAllProducts(int pageNumber, String searchKey);
    void deleteProductDetails(Integer productId);
    Product getProductDetailsById(Integer productId);
    List<Product> getProductDetails(boolean isSingleProductCheckout, Integer productId);
}