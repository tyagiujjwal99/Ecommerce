package com.ecommerce.productsbackend.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.ecommerce.productsbackend.model.User;
import com.ecommerce.productsbackend.repo.UserRepository;
import com.ecommerce.productsbackend.service.UserService;

import lombok.extern.slf4j.Slf4j;


@Service
@Slf4j
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
    public User addUser(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        log.debug("User added successfully: {}", user.getEmail());
        return user;
    }

    @Override
    public User createUser(User user) throws Exception {
        User local = userRepository.findByEmail(user.getEmail());
        if (local != null) {
            log.debug("User with email '{}' already exists", user.getEmail());
            throw new Exception("User already present with email: " + user.getEmail());
        } else {
            local = userRepository.save(user);
            log.debug("New user created: {}", user.getEmail());
        }
        return local;
    }

    @Override
    public User getUser(String email) {
        User user = userRepository.findByEmail(email);
        if (user != null) {
            log.debug("User found by email: {}", email);
        } else {
            log.debug("No user found with email: {}", email);
        }
        return user;
    }

    @Override
    public void deleteUser(String email) {
        User user = userRepository.findByEmail(email);
        if (user != null) {
            userRepository.delete(user);
            log.debug("User deleted successfully: {}", email);
        } else {
            log.debug("User with email '{}' not found for deletion", email);
        }
    }
    
    @Override
    public User updateUser(User user) throws Exception {
        User existingUser = userRepository.findByEmail(user.getEmail());
        if (existingUser == null) {
            log.debug("User with email '{}' not found for update", user.getEmail());
            throw new Exception("User not found with email: " + user.getEmail());
        }

        // Update the necessary fields of existingUser with values from updatedUser
        existingUser.setEmail(user.getEmail());
        existingUser.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        existingUser.setRoles(user.getRoles());
        existingUser.setFirstname(user.getFirstname());
        existingUser.setLastname(user.getLastname());

        User savedUser = userRepository.save(existingUser);
        log.debug("User updated successfully: {}", user.getEmail());
        return savedUser;
    }

}
