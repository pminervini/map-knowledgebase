package com.neuralnoise.map.model.map.crowd;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.neuralnoise.map.model.NamedEntity;

@Entity
@Table(name = "contribution")
public class CrowdContribution extends NamedEntity {

	private static final long serialVersionUID = -2391518842596255611L;

	private static final Logger log = LoggerFactory.getLogger(CrowdContribution.class);
	
	@Column(name = "type", length = 1024)
	@NotEmpty
	protected String type;
	
	@Column(name = "description", length = 8192)
	protected String description;
	
	@Column(name = "contributor")
	//@NotEmpty(message = "Email address cannot be empty")
	@Email(message = "Invalid email address, e.g. valid email address: example@gmail.com")
	protected String contributor;
	
	@Column(name = "start_date")
	//@NotNull
	@Type(type="org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	protected DateTime startDate;

	@Column(name = "end_date")
	//@NotNull
	@Type(type="org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	protected DateTime endDate;
	
	public CrowdContribution() { }

	public String getType() {
		return this.type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getContributor() {
		return contributor;
	}

	public void setContributor(String contributor) {
		this.contributor = contributor;
	}
	
	public DateTime getStartDate() {
		return startDate;
	}

	public void setStartDate(DateTime startDate) {
		this.startDate = startDate;
	}

	public DateTime getEndDate() {
		return endDate;
	}

	public void setEndDate(DateTime endDate) {
		this.endDate = endDate;
	}

	@Override
	public String toString() {
		return "CrowdContribution [type=" + type + ", description=" + description + ", startDate=" + startDate + ", endDate=" + endDate + ", name=" + name + ", id=" + id + "]";
	}
	
}
