package com.rajkumar.mongodb.runner;

import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import javax.annotation.Priority;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rajkumar.mongodb.domain.FlightInformation;

/**
 * 
 * @author Rajkumar
 *
 */
@Component
@Priority(5)
public class ApplicationDataUpdateAndRemoveApplicationRunner implements ApplicationRunner {

	private static final String ERROR_IN_JSON_PROCESSING = "Error in json processing: ";

	static final Logger logger = LogManager.getLogger(ApplicationDataUpdateAndRemoveApplicationRunner.class);

	private MongoTemplate mongoTemplate;

	public ApplicationDataUpdateAndRemoveApplicationRunner(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		markAllFlightsToRomeAsDelayed();
		removeFlightsWithDurationLessThanTwoHours();
	}

	private void markAllFlightsToRomeAsDelayed() {
		Query romeQuery = Query.query(Criteria.where("destination").is("Rome"));

		ObjectMapper mapper = new ObjectMapper();
		String fligtDetails = mongoTemplate.find(romeQuery, FlightInformation.class).stream().map(eachFlight -> {
			try {
				return mapper.writeValueAsString(eachFlight);
			} catch (JsonProcessingException exception) {
				logger.error(ERROR_IN_JSON_PROCESSING, exception);
				return eachFlight.toString();
			}

		}).collect(Collectors.joining(","));
		logger.info("Before Update: {}", fligtDetails);

		Update setUpdate = Update.update("isDelayed", true);
		mongoTemplate.updateMulti(romeQuery, setUpdate, FlightInformation.class);

		fligtDetails = mongoTemplate.find(romeQuery, FlightInformation.class).stream().map(eachFlight -> {
			try {
				return mapper.writeValueAsString(eachFlight);
			} catch (JsonProcessingException exception) {
				logger.error(ERROR_IN_JSON_PROCESSING, exception);
				return eachFlight.toString();
			}

		}).collect(Collectors.joining(","));
		logger.info("After Update: {}", fligtDetails);
	}

	private void removeFlightsWithDurationLessThanTwoHours() {
		Query lessThanTwoHoursQuery = Query.query(Criteria.where("duration").lt(TimeUnit.HOURS.toMinutes(2)));

		ObjectMapper mapper = new ObjectMapper();
		String fligtDetails = mongoTemplate.find(lessThanTwoHoursQuery, FlightInformation.class).stream().map(eachFlight -> {
			try {
				return mapper.writeValueAsString(eachFlight);
			} catch (JsonProcessingException exception) {
				logger.error(ERROR_IN_JSON_PROCESSING, exception);
				return eachFlight.toString();
			}

		}).collect(Collectors.joining(","));
		logger.info("Before Removal: {}", fligtDetails);

		mongoTemplate.findAllAndRemove(lessThanTwoHoursQuery, FlightInformation.class);

		fligtDetails = mongoTemplate.find(lessThanTwoHoursQuery, FlightInformation.class).stream().map(eachFlight -> {
			try {
				return mapper.writeValueAsString(eachFlight);
			} catch (JsonProcessingException exception) {
				logger.error(ERROR_IN_JSON_PROCESSING, exception);
				return eachFlight.toString();
			}

		}).collect(Collectors.joining(","));
		logger.info("After Removal: {}", fligtDetails);
	}

}
