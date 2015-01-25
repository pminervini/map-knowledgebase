package com.neuralnoise.map.data.map.newsletter;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.neuralnoise.map.data.AbstractDAO;
import com.neuralnoise.map.model.map.newsletter.Subscription;

@Repository
@Transactional
public class SubscriptionDAO extends AbstractDAO<Subscription, String> {

	private static final Logger log = LoggerFactory.getLogger(SubscriptionDAO.class);
	
	public SubscriptionDAO() {
		super(Subscription.class);
	}

}
