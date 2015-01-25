package com.neuralnoise.map.service.map.crowd;

import java.util.List;

import com.neuralnoise.map.model.map.crowd.CrowdContribution;

public interface CrowdContributionService {

	public CrowdContribution create(CrowdContribution contribution);
	
	public List<CrowdContribution> getAll();
	
}
