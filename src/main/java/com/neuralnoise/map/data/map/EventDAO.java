package com.neuralnoise.map.data.map;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.neuralnoise.map.model.map.Event;

@Repository
@Transactional
public class EventDAO extends AbstractContributedDAO<Event, Long> {

	private static final Logger log = LoggerFactory.getLogger(EventDAO.class);

	public EventDAO() {
		super(Event.class);
	}
	
	@SuppressWarnings("unchecked")
	public List<Event> findBetween(DateTime startDate, DateTime endDate) {
		Session session = getEntityManager().unwrap(Session.class);

		Criteria criteria = session.createCriteria(clazz);
		
		if (startDate != null && endDate != null) {
			// overlaps between the two intervals
			criteria.add(Restrictions.disjunction()
				.add(Restrictions.between("startDate", startDate, endDate))
				.add(Restrictions.between("endDate", startDate, endDate)));
		} else if (startDate != null) {
			// start/end dates after a provided start date
			criteria.add(Restrictions.disjunction()
					.add(Restrictions.ge("startDate", startDate))
					.add(Restrictions.ge("endDate", startDate)));
		} else if (endDate != null) {
			// start/end dates before a provided end date
			criteria.add(Restrictions.disjunction()
					.add(Restrictions.le("startDate", endDate))
					.add(Restrictions.le("endDate", endDate)));
		} else {
			// no search criteria provided at all
		}
		
		return criteria.list();
	}

}
