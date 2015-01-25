package com.neuralnoise.map.service.security;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.neuralnoise.map.model.security.UserEntity;

public interface SecurityService extends UserDetailsService {

	public UserEntity current() throws UsernameNotFoundException;

	public UserEntity getById(String name);

	// XXX: temporary
	public void login(String userName, String password);

}
