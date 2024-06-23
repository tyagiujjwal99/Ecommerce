package com.ecommerce.productsbackend.serviceimpl;

import java.util.LinkedHashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.productsbackend.model.Delivery;
import com.ecommerce.productsbackend.repo.DeliveryRepository;
import com.ecommerce.productsbackend.service.DeliveryService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class DeliveryServiceImpl implements DeliveryService {

	@Autowired
	private DeliveryRepository deliveryRepository;
	
	@Override
    public Delivery addDelivery(Delivery delivery) {
        log.debug("Adding delivery: {}", delivery);
        return this.deliveryRepository.save(delivery);
    }

    @Override
    public Set<Delivery> findAllDelivery() {
        log.debug("Fetching all deliveries");
        return new LinkedHashSet<>(this.deliveryRepository.findAll());
    }

    @Override
    public Delivery findOneDelivery(int productcode, int pincode) {
        log.debug("Fetching delivery for productcode: {} and pincode: {}", productcode, pincode);
        return this.deliveryRepository.findByProductCodeAndPincode(productcode, pincode);
    }

}
