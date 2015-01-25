package com.neuralnoise.map.model.map;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Entity
@Table(name = "museum")
@PrimaryKeyJoinColumn(name="id", referencedColumnName="id")
public class Museum extends ContributedEntity {

	private static final Logger log = LoggerFactory.getLogger(Museum.class);

	private static final long serialVersionUID = 8875525963977986966L;

}
