package com.neuralnoise.map.service.map.newsletter;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.neuralnoise.map.data.map.newsletter.SubscriptionDAO;
import com.neuralnoise.map.model.map.newsletter.Subscription;

@Service
public class NewsletterServiceImpl implements NewsletterService {

	private static final Logger log = LoggerFactory.getLogger(NewsletterServiceImpl.class);

	@Autowired
	protected SubscriptionDAO subscriptionDAO;

	@Override
	@Transactional(readOnly = false)
	public Subscription create(Subscription subscription) {
		return subscriptionDAO.create(subscription);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Subscription> getAll() {
		return subscriptionDAO.getAll();
	}

}

