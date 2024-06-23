package com.ecommerce.productsbackend.serviceimpl;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ecommerce.productsbackend.model.User;
import com.ecommerce.productsbackend.repo.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
	    // Fetch user from repository
	    User user = userRepository.findByEmail(email);

	    // Log the user details for debugging
	    if (user != null) {
	        log.debug("User found: {}", user);
	        log.debug("User roles: {}", user.getRoles());
	    } else {
	        log.debug("No user found with email: {}", email);
	        throw new UsernameNotFoundException("No user found with email: " + email);
	    }

	    // Map roles to Spring Security's GrantedAuthority
	    List<GrantedAuthority> authorities = Arrays.stream(user.getRoles().split(","))
	            .map(SimpleGrantedAuthority::new)
	            .collect(Collectors.toList());

	    // Return UserDetails object required by Spring Security
	    return new org.springframework.security.core.userdetails.User(
	            user.getEmail(),
	            user.getPassword(),
	            authorities
	    );
	}

}
