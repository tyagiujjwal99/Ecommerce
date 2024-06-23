package com.ecommerce.productsbackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.productsbackend.model.Delivery;
import com.ecommerce.productsbackend.service.DeliveryService;

import lombok.extern.slf4j.Slf4j;

@RestController
//@CrossOrigin("*")
@Slf4j
@RequestMapping("/delivery/")
public class DeliveryController {
	@Autowired
	private DeliveryService deliveryService;
	
	@PostMapping("add")
    public ResponseEntity<Delivery> add(@RequestBody Delivery delivery) {
        log.debug("Received request to add delivery: {}", delivery);
        Delivery addedDelivery = this.deliveryService.addDelivery(delivery);
        log.debug("Added delivery: {}", addedDelivery);
        return ResponseEntity.ok(addedDelivery);
    }

    @GetMapping
    public ResponseEntity<?> deliveries() {
        log.debug("Fetching all deliveries");
        return ResponseEntity.ok(this.deliveryService.findAllDelivery());
    }

    @GetMapping("{productcode}/{pincode}")
    public Delivery getDeliverable(@PathVariable("productcode") int productId,
                                   @PathVariable("pincode") int pincode) {
        log.debug("Fetching delivery for productId: {} and pincode: {}", productId, pincode);
        return deliveryService.findOneDelivery(productId, pincode);
    }
}
