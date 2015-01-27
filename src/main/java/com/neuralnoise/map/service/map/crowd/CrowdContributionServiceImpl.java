package com.neuralnoise.map.service.map.crowd;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.neuralnoise.map.data.map.crowd.CrowdContributionDAO;
import com.neuralnoise.map.model.map.crowd.CrowdContribution;

@Service
public class CrowdContributionServiceImpl implements CrowdContributionService {

	private static final Logger log = LoggerFactory.getLogger(CrowdContributionServiceImpl.class);
	
	@Autowired
	protected CrowdContributionDAO crowdContributionDAO;
	
	@Override
	@Transactional(readOnly = false)
	public CrowdContribution create(CrowdContribution contribution) {
		System.out.println("XXX 2: " + contribution);
		return crowdContributionDAO.create(contribution);
	}

	@Override
	@Transactional(readOnly = true)
	public List<CrowdContribution> getAll() {
		return crowdContributionDAO.getAll();
	}

}
