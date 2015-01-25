package com.neuralnoise.map.service.map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.neuralnoise.map.data.map.OrganizationDAO;
import com.neuralnoise.map.model.map.Organization;
import com.neuralnoise.map.service.map.util.AbstractContributedEntityServiceImpl;

@Service
public class OrganizationServiceImpl extends AbstractContributedEntityServiceImpl<Organization, OrganizationDAO> implements OrganizationService {

	private static final Logger log = LoggerFactory.getLogger(OrganizationServiceImpl.class);

}
