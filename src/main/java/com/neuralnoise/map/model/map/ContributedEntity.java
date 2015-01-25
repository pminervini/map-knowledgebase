package com.neuralnoise.map.model.map;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.neuralnoise.map.model.NamedEntity;
import com.neuralnoise.map.model.geo.Location;
import com.neuralnoise.map.model.security.UserEntity;

@Entity
@Table(name="contributed_entity")
@Inheritance(strategy=InheritanceType.JOINED)
@PrimaryKeyJoinColumn(name="id", referencedColumnName="id")
public class ContributedEntity extends NamedEntity {

	private static final long serialVersionUID = -4161533353336890677L;

	private static final Logger log = LoggerFactory.getLogger(ContributedEntity.class);

	@NotNull
	@ManyToOne(fetch = FetchType.EAGER)
	@Fetch(FetchMode.JOIN)
	protected UserEntity contributor;

	@Column(name = "description", length = 8192)
	protected String description;

	@Embedded
	protected Location location;
	
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@Fetch(FetchMode.JOIN)
	//@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "entity_annotation",
		joinColumns = { @JoinColumn(name = "id") },
		inverseJoinColumns = { @JoinColumn(name = "name") })
	protected Set<Annotation> annotations = new HashSet<Annotation>();

	public UserEntity getContributor() {
		return contributor;
	}

	public void setContributor(UserEntity contributor) {
		this.contributor = contributor;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}
	
	public Set<Annotation> getAnnotations() {
		return annotations;	
	}
	
	public void setAnnotations(Set<Annotation> annotations) {
		this.annotations = annotations;
	}

	@Override
	@JsonIgnore
	public Map<String, Object> getProperties() {
		Map<String, Object> properties = super.getProperties();
		if (description != null) {
			properties.put("description", description);
		}
		if (location != null) {
			properties.putAll(location.getProperties());
		}
		
		Table table = this.getClass().getAnnotation(Table.class);
		if (table != null) {
			properties.put("type", table.name());	
		}
		
		return properties;
	}

	@Override
	public String toString() {
		return "ContributedEntity [contributor=" + contributor + ", description=" + description + ", location=" + location + ", name=" + name + ", id=" + id + "]";
	}

}
