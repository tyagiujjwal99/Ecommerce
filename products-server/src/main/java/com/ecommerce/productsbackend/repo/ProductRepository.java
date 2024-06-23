package com.ecommerce.productsbackend.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ecommerce.productsbackend.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
	 @Query("SELECT p from Product p WHERE " + "CONCAT(p.code, p.brand, p.name,p.price, p.details) LIKE %?1%")
	 List<Product> getSearchProducts(String str);
}