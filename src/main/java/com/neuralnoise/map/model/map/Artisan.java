package com.neuralnoise.map.model.map;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Entity
@Table(name = "artisan")
@PrimaryKeyJoinColumn(name="id", referencedColumnName="id")
public class Artisan extends ContributedEntity {

	private static final long serialVersionUID = -3414460840023103502L;

	private static final Logger log = LoggerFactory.getLogger(Artisan.class);
	
}
