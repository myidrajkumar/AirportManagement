package com.rajkumar.mongodb.queries;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.rajkumar.mongodb.domain.FlightInformation;
import com.rajkumar.mongodb.domain.FlightType;

@Service
public class FlightInformationQueries {

	private MongoTemplate mongoTemplate;
	
	public FlightInformationQueries(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}
	
	public List<FlightInformation> findAll(String field, int pageNo, int pageSize) {
		Query allPagedAndSorted = new Query()
				.with(Sort.by(Sort.Direction.ASC, field))
				.with(PageRequest.of(pageNo, pageSize));
		return mongoTemplate.find(allPagedAndSorted, FlightInformation.class);
	}
	
	public List<FlightInformation> findAll() {
		return mongoTemplate.findAll(FlightInformation.class);
	}
	
	public FlightInformation findSingleById(String id) {
		return mongoTemplate.findById(id, FlightInformation.class);
	}
	
	public long countInternationalFlights(FlightType flightType) {
		Query flightTypeQuery = Query.query(Criteria.where("type").is(flightType));
		return mongoTemplate.count(flightTypeQuery, FlightInformation.class);
	}
	
	public List<FlightInformation> findForFiled(String fieldName, String filedValue) {
		Query fieldQuery = Query.query(Criteria.where(fieldName).is(filedValue));
		return mongoTemplate.find(fieldQuery, FlightInformation.class);
	}
	
	public List<FlightInformation> findByDuration(int minMinutes, int maxMinutes) {
		Query durationQuery = Query
				.query(Criteria.where("duration").gte(minMinutes).lte(maxMinutes))
				.with(Sort.by(Sort.Direction.DESC, "duration"));
		return mongoTemplate.find(durationQuery, FlightInformation.class);
	}
	
	public List<FlightInformation> findDelayedAtDeparture(String departure, boolean isDelayed) {
		Query delayedDepartureQuery = Query
				.query(Criteria.where("departure").is(departure)
						.and("isDelayed").is(isDelayed));
		return mongoTemplate.find(delayedDepartureQuery, FlightInformation.class);
	}
	
	public List<FlightInformation> findRelatedToCityAndNotDelayed(String city) {
		Query delayedDepartureQuery = Query
				.query(new Criteria()
						.orOperator(Criteria.where("departure").is(city),
								Criteria.where("destination").is(city))
						.andOperator(Criteria.where("isDelayed").is(false)));
		return mongoTemplate.find(delayedDepartureQuery, FlightInformation.class);
	}
	
	public List<FlightInformation> findInnerDocument(String aircraftModel) {
		Query delayedDepartureQuery = Query
				.query(Criteria.where("aircraft.model").is(aircraftModel));
		return mongoTemplate.find(delayedDepartureQuery, FlightInformation.class);
	}
}
