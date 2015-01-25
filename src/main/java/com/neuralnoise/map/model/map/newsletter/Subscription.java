package com.neuralnoise.map.model.map.newsletter;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "subscription")
public class Subscription implements Serializable {

	private static final long serialVersionUID = -345358584757450600L;

	public Subscription() { }
	
	@Id
	@Column(name = "email")
	@NotEmpty(message = "Email address cannot be empty")
	@Email(message = "Invalid email address, e.g. valid email address: example@gmail.com")
	protected String email;

	@Column(name = "name")
	//@NotEmpty
	protected String name;

	@Column(name = "city")
	//@NotEmpty
	protected String city;
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}
	
	@Override
	public String toString() {
		return "Subscription [email=" + email + ", name=" + name + ", city=" + city + "]";
	}
}
