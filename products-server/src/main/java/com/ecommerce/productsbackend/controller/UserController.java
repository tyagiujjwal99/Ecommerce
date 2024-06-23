package com.ecommerce.productsbackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.productsbackend.model.User;
import com.ecommerce.productsbackend.service.UserService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/users/")
//@CrossOrigin(origins = "*")
@Slf4j
public class UserController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	 @PostMapping("add")
	    public User createUser(@RequestBody User user) throws Exception {
	        log.debug("Received request to create user: {}", user.getEmail());
	        user.setPassword(this.bCryptPasswordEncoder.encode(user.getPassword()));
	        return this.userService.createUser(user);
	    }

	    @GetMapping("{email}")
	    public User getUser(@PathVariable("email") String email) {
	        log.debug("Fetching user with email: {}", email);
	        return this.userService.getUser(email);
	    }

	    @DeleteMapping("delete/{email}")
	    public String deleteUser(@PathVariable("email") String email) {
	        log.debug("Deleting user with email: {}", email);
	        this.userService.deleteUser(email);
	        return "User deleted successfully ";
	    }

	    @PutMapping("update")
	    public User updateUser(@RequestBody User user) throws Exception {
	        log.debug("Updating user: {}", user.getEmail());
	        return this.userService.updateUser(user);
	    }

}
