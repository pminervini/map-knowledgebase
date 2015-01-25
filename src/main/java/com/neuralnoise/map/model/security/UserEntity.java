package com.neuralnoise.map.model.security;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "user_entity")
public class UserEntity implements Serializable {

	private static final long serialVersionUID = 2119552988950072047L;

	@Id
	@Column(name = "name")
	@NotEmpty
	protected String name;

	@Column(name = "digest")
	@NotEmpty
	protected String digest;

	@Column(name = "admin")
	@NotEmpty
	protected boolean admin;

	public void setId(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public String getDigest() {
		return digest;
	}

	public void setDigest(String digest) {
		this.digest = digest;
	}

	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}

}
