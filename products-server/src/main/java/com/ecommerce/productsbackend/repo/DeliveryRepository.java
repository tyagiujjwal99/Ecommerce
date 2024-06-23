package com.ecommerce.productsbackend.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.productsbackend.model.Delivery;

public interface DeliveryRepository extends JpaRepository<Delivery, Integer>{
	  Delivery findByProductCodeAndPincode(int productcode,int pincode);
}
