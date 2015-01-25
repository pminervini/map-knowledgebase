package com.neuralnoise.map.model;

import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="named_entity")
@Inheritance(strategy=InheritanceType.JOINED)
public class NamedEntity extends BaseEntity {

	private static final long serialVersionUID = -964173231862367581L;

	@Column(name = "name", length = 1024)
	@NotEmpty
	protected String name;

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	@Override
	public String toString() {
		return this.getName();
	}

	@Override
	@JsonIgnore
	public Map<String, Object> getProperties() {
		Map<String, Object> properties = super.getProperties();
		if (name != null) {
			properties.put("name", name);
		}
		return properties;
	}

}
