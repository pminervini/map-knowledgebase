package com.neuralnoise.map.service.map.newsletter;

import java.util.List;

import com.neuralnoise.map.model.map.newsletter.Subscription;

public interface NewsletterService {

	public Subscription create(Subscription subscription);
	
	public List<Subscription> getAll();
	
}
