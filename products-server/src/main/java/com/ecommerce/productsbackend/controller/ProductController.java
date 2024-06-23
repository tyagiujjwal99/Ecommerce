package com.ecommerce.productsbackend.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.productsbackend.model.Product;
import com.ecommerce.productsbackend.service.ProductService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/products")
@CrossOrigin("*")
@Slf4j
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	@PostMapping("/add")
	public ResponseEntity<Product> addProduct(@RequestBody Product product) {
	    log.info("Adding product: {}", product.getName());
	    Product product1 = this.productService.addProduct(product);
	    log.info("Product added successfully: {}", product1);
	    return ResponseEntity.ok(product1);
	}

	@GetMapping("/{id}")
	public Product getProduct(@PathVariable("id") Integer id) {
	    log.info("Fetching product with ID: {}", id);
	    return this.productService.getProduct(id);
	}

	@GetMapping("/getallproduct")
	public Set<Product> getAllProduct() {
	    log.info("Fetching all products");
	    return this.productService.getProducts();
	}

	@GetMapping("/search/{str}")
	public Set<Product> getSearchProduct(@PathVariable("str") String str) {
	    log.info("Searching for products with keyword: {}", str);
	    return this.productService.getSearchProduct(str);
	}

	@PutMapping("/update/{code}")
	public ResponseEntity<Product> updateProduct(@PathVariable("code") Integer code, @RequestBody Product updatedProduct) {
	    log.info("Updating product with code: {}", code);
	    Product product = productService.getProduct(code);
	    if (product != null) {
	        updatedProduct.setCode(code);
	        Product updated = productService.updateProduct(updatedProduct);
	        log.info("Product updated successfully: {}", updated);
	        return ResponseEntity.ok(updated);
	    } else {
	        log.warn("Product with code {} not found", code);
	        return ResponseEntity.notFound().build();
	    }
	}

	@DeleteMapping("/delete/{code}")
	public ResponseEntity<String> deleteProduct(@PathVariable("code") Integer code) {
	    log.info("Deleting product with code: {}", code);
	    productService.deleteProduct(code);
	    return ResponseEntity.ok("Product Deleted.");
	}

	


}
