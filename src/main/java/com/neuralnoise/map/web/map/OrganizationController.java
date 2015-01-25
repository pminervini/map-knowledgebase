package com.neuralnoise.map.web.map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.neuralnoise.map.model.map.Organization;
import com.neuralnoise.map.service.map.OrganizationService;
import com.neuralnoise.map.web.map.util.AbstractContributedEntityController;

@Controller
@RequestMapping("/organization")
public class OrganizationController extends AbstractContributedEntityController<Organization, OrganizationService> {

	private static final Logger log = LoggerFactory.getLogger(OrganizationController.class);

}
