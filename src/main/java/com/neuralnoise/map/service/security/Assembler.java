package com.neuralnoise.map.service.security;

import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;
import com.neuralnoise.map.model.security.UserEntity;

@Service("assembler")
public class Assembler {

	private static final Logger log = LoggerFactory.getLogger(Assembler.class);

	@Transactional(readOnly = true)
	public User buildUserFromUserEntity(UserEntity userEntity) {

		String username = userEntity.getName();
		String digest = userEntity.getDigest();

		log.info("User ({}, {})", username, digest);

		Collection<GrantedAuthority> authorities = Lists.newLinkedList();

		authorities.add(new SimpleGrantedAuthority("ROLE_USER"));

		if (userEntity.isAdmin()) {
			authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
		}

		User user = new User(username, digest, true, true, true, true, authorities);
		return user;
	}
}