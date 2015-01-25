package com.neuralnoise.map.model.map;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Entity
@Table(name = "organization")
@PrimaryKeyJoinColumn(name="id", referencedColumnName="id")
public class Organization extends ContributedEntity {

	private static final Logger log = LoggerFactory.getLogger(Organization.class);

	private static final long serialVersionUID = -8087761633723818718L;

}
