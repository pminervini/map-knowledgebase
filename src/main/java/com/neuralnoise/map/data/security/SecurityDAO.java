package com.neuralnoise.map.data.security;

import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.neuralnoise.map.data.AbstractDAO;
import com.neuralnoise.map.model.security.UserEntity;

@Repository
@Transactional
public class SecurityDAO extends AbstractDAO<UserEntity, String> {

	public SecurityDAO() {
		super(UserEntity.class);
	}

}
