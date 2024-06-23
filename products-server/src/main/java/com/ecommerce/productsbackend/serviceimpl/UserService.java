package com.ecommerce.productsbackend.serviceimpl;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.ecommerce.productsbackend.model.User;


public class UserService  implements UserDetails {
	private static final long serialVersionUID = 1L;
	private String email;
	private String firstname;
	private String lastname;
	private String password;
    private List<GrantedAuthority> authorities;

    
    public UserService(User user) {
        this.firstname = user.getFirstname();
        this.password = user.getPassword();
        this.lastname = user.getLastname();
        this.email =  user.getEmail();
        this.authorities = Arrays.stream(user.getRoles().split(","))
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return null;
	}

	@Override
	public String getUsername() {
		return null;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
