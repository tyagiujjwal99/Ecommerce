package com.ecommerce.productsbackend.service;

import com.ecommerce.productsbackend.model.User;

public interface UserService {
	
	public User addUser(User user);

	public User createUser(User user) throws Exception;

	public User getUser(String email);

	public void deleteUser(String email);

	public User updateUser(User user) throws Exception;

}
