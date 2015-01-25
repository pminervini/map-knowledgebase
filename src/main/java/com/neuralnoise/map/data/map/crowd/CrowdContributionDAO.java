package com.neuralnoise.map.data.map.crowd;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.neuralnoise.map.data.AbstractNamedDAO;
import com.neuralnoise.map.model.map.crowd.CrowdContribution;

@Repository
@Transactional
public class CrowdContributionDAO extends AbstractNamedDAO<CrowdContribution, Long> {

	private static final Logger log = LoggerFactory.getLogger(CrowdContributionDAO.class);
	
	public CrowdContributionDAO() {
		super(CrowdContribution.class);
	}
	
}
