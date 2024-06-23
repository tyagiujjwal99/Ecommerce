package com.ecommerce.productsbackend.service;

import java.util.Set;

import com.ecommerce.productsbackend.model.Delivery;

public interface DeliveryService {
	public Set<Delivery> findAllDelivery();

	
	public Delivery addDelivery(Delivery delivery);


	public Delivery findOneDelivery(int productcode, int pincode);

}
