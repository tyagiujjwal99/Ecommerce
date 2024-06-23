package com.ecommerce.productsbackend.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.productsbackend.config.JwtUtil;
import com.ecommerce.productsbackend.model.JwtRequest;
import com.ecommerce.productsbackend.model.JwtResponse;
import com.ecommerce.productsbackend.serviceimpl.UserDetailsServiceImpl;

import lombok.extern.slf4j.Slf4j;

@RestController
//@CrossOrigin("*")
@Slf4j
public class AuthenticateController {

	@Autowired
	private UserDetailsServiceImpl userDetailsServiceImpl;

	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	private AuthenticationManager authenticationManager;

	@PostMapping("/token")
    public ResponseEntity<?> genrateToken(@RequestBody JwtRequest jwtRequest) throws Exception {
        log.debug("Received request to generate token for user: {}", jwtRequest.getUsername());
        try {
            this.authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(jwtRequest.getUsername(), jwtRequest.getPassword()));
        } catch (Exception e) {
            log.error("Authentication failed for user: {}", jwtRequest.getUsername(), e);
            throw new Exception("Bad credentials");
        }

        UserDetails userDetails = this.userDetailsServiceImpl.loadUserByUsername(jwtRequest.getUsername());
        String token = this.jwtUtil.generateToken(userDetails);
        log.debug("Generated token for user {}: {}", jwtRequest.getUsername(), token);
        return ResponseEntity.ok(new JwtResponse(token));
    }

    @GetMapping("/current-user")
    public User getCurrentUser(Principal principal) {
        String username = principal.getName();
        log.debug("Fetching current user details for username: {}", username);
        return (User) this.userDetailsServiceImpl.loadUserByUsername(username);
    }
}
