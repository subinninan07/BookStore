package com.bookstore.serviceImpl;

import com.bookstore.configuration.JwtRequestFilter;

import com.bookstore.dao.CartDao;
import com.bookstore.dao.ProductDao;
import com.bookstore.dao.UserDao;
import com.bookstore.entity.Cart;
import com.bookstore.entity.Product;
import com.bookstore.entity.User;
import com.bookstore.exception.ProductAdditionException;
import com.bookstore.exception.ProductNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl {

    @Autowired
    private ProductDao productDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private CartDao cartDao;

    public ProductServiceImpl(ProductDao productDao2) {
		this.productDao=productDao2;// TODO Auto-generated constructor stub
	}

	public Product addNewProduct(Product product) {
        try {
            return productDao.save(product);
        } catch (Exception e) {
            throw new ProductAdditionException("Failed to add product: " + e.getMessage());
        }
    }

    public List<Product> getAllProducts(int pageNumber, String searchKey) {
        Pageable pageable = PageRequest.of(pageNumber, 8);

        if (searchKey.equals("")) {
            return productDao.findAll(pageable);
        } else {
            return productDao.findByProductNameContainingIgnoreCaseOrProductDescriptionContainingIgnoreCase(searchKey, searchKey, pageable);
        }
    }

    public void deleteProductDetails(Integer productId) {
        try {
            productDao.deleteById(productId);
        } catch (Exception e) {
            throw new ProductNotFoundException("Product not found or deletion failed: " + e.getMessage());
        }
    }

    public Product getProductDetailsById(Integer productId) {
        return productDao.findById(productId).orElseThrow(() -> new ProductNotFoundException("Product not found with ID: " + productId));
    }

    public List<Product> getProductDetails(boolean isSingleProductCheckout, Integer productId) {
        if (isSingleProductCheckout && productId != 0) {
            List<Product> list = new ArrayList<>();
            Product product = getProductDetailsById(productId);
            list.add(product);
            return list;
        } else {
            String username = JwtRequestFilter.CURRENT_USER;
            User user = userDao.findById(username).orElseThrow(() -> new RuntimeException("User not found"));
            List<Cart> carts = cartDao.findByUser(user);
            return carts.stream().map(Cart::getProduct).collect(Collectors.toList());
        }
    }
}

