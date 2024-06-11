package com.bookstore.controller;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.bookstore.entity.ImageModel;
import com.bookstore.entity.Product;
import com.bookstore.exception.ProductNotFoundException;
import com.bookstore.serviceImpl.ProductServiceImpl;


@RestController
public class ProductController {

    @Autowired
    private ProductServiceImpl productServiceImpl;

    

    
    @PreAuthorize("hasRole('Admin')")
	@PostMapping(value = {"/addNewProduct"}, consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
	public Product addNewProduct(@RequestPart("product") Product product,
									@RequestPart("imageFile") MultipartFile[] file) {		
	
		try {
			Set<ImageModel> images = uplodImage(file);
			product.setProductImages(images);
			return productServiceImpl.addNewProduct(product); 
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
		
		
	}
	
	
	public Set<ImageModel> uplodImage(MultipartFile[] multipartFiles) throws IOException{
		
		Set<ImageModel> imageModels = new HashSet<>();
		
		for(MultipartFile file: multipartFiles) {
			ImageModel imageModel = new ImageModel(
					file.getOriginalFilename(),
					file.getContentType(),
					file.getBytes());
			imageModels.add(imageModel);
		}
		return imageModels;
	}
    
    


    @GetMapping({"/getAllProducts"})
    public List<Product> getAllProducts(@RequestParam(defaultValue = "0") int pageNumber,
                                        @RequestParam(defaultValue = "") String searchKey) {
        return productServiceImpl.getAllProducts(pageNumber, searchKey);
    }

    @GetMapping({"/getProductDetailsById/{productId}"})
    public Product getProductDetailsById(@PathVariable("productId") Integer productId) {
        try {

            return productServiceImpl.getProductDetailsById(productId);
        } catch (ProductNotFoundException e) {
            throw new ProductNotFoundException("Product not found with ID: " + productId);
        }
    }

    @PreAuthorize("hasRole('Admin')")
    @DeleteMapping({"/deleteProductDetails/{productId}"})
    public void deleteProductDetails(@PathVariable("productId") Integer productId) {
        try {

            productServiceImpl.deleteProductDetails(productId);
        } catch (ProductNotFoundException e) {
            throw new ProductNotFoundException("Product not found or deletion failed: " + e.getMessage());
        }
    }

    @PreAuthorize("hasRole('User')")
    @GetMapping({"/getProductDetails/{isSingleProductCheckout}/{productId}"})
    public List<Product> getProductDetails(@PathVariable(name = "isSingleProductCheckout") boolean isSingleProductCheckout,
                                            @PathVariable(name = "productId") Integer productId) {
        try {

            return productServiceImpl.getProductDetails(isSingleProductCheckout, productId);
        } catch (RuntimeException e) {
            throw new RuntimeException("Failed to fetch product details: " + e.getMessage());
        }
    }
}
