package com.neuralnoise.map.model.map;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "annotation")
public class Annotation implements Serializable {

	private static final long serialVersionUID = 2119552988950072047L;

	@Id
	@Column(name = "name")
	@NotEmpty
	protected String name;

	@Column(name = "description", length = 8192)
	protected String description;

	@JsonIgnore
	@ManyToMany(fetch = FetchType.EAGER, mappedBy = "annotations")
	@Fetch(FetchMode.JOIN)
	//@ManyToMany(fetch = FetchType.LAZY, mappedBy = "annotations")
	protected Set<ContributedEntity> contributedEntities = new HashSet<ContributedEntity>();

	public void setId(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public Set<ContributedEntity> getContributedEntities() {
		return contributedEntities;
	}

	public void setContributedEntities(Set<ContributedEntity> contributedEntities) {
		this.contributedEntities = contributedEntities;
	}

}
