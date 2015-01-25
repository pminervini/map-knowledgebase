package com.neuralnoise.map.data.map;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.neuralnoise.map.model.map.Organization;

@Repository
@Transactional
public class OrganizationDAO extends AbstractContributedDAO<Organization, Long> {

	private static final Logger log = LoggerFactory.getLogger(OrganizationDAO.class);

	public OrganizationDAO() {
		super(Organization.class);
	}

}
