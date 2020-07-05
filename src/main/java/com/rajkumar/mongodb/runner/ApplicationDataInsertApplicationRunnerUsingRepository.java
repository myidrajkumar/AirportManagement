package com.rajkumar.mongodb.runner;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Priority;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rajkumar.mongodb.domain.Aircraft;
import com.rajkumar.mongodb.domain.FlightInformation;
import com.rajkumar.mongodb.domain.FlightType;
import com.rajkumar.mongodb.repository.FlightInformationRepository;

/**
 * 
 * @author Rajkumar
 *
 */
@Component
@Priority(6)
public class ApplicationDataInsertApplicationRunnerUsingRepository implements ApplicationRunner {

	static final Logger logger = LogManager.getLogger(ApplicationDataInsertApplicationRunnerUsingRepository.class);
	
    private FlightInformationRepository flightInformationRepository;
	
	public ApplicationDataInsertApplicationRunnerUsingRepository(FlightInformationRepository flightInformationRepository) {
		this.flightInformationRepository = flightInformationRepository;
	}
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
		removeAllTheFlights();
		insertMultipleFlights();
	}

	private void removeAllTheFlights() {
		flightInformationRepository.deleteAll();
	}

	private void insertMultipleFlights() {
		FlightInformation flightOne = new FlightInformation();
		flightOne.setDelayed(false);
		flightOne.setDepartureCity("Rome");
		flightOne.setDestinationCity("Paris");
		flightOne.setDepartureDate(LocalDate.of(2019, Month.MARCH, 12));
		flightOne.setType(FlightType.INTERNATIONAL);
		flightOne.setDurationMin(80);
		flightOne.setAircraft(new Aircraft("737", 180));
		flightOne.setDescription("Flight from Rome to Paris");
		
		FlightInformation flightTwo = new FlightInformation();
		flightTwo.setDelayed(false);
		flightTwo.setDepartureCity("New York");
		flightTwo.setDestinationCity("Copenhagen");
		flightTwo.setDepartureDate(LocalDate.of(2019, Month.MAY, 11));
		flightTwo.setType(FlightType.INTERNATIONAL);
		flightTwo.setDurationMin(600);
		flightTwo.setAircraft(new Aircraft("747", 300));
		flightTwo.setDescription("Flight from NY to Copenhagen via Rome");
		
		FlightInformation flightThree = new FlightInformation();
		flightThree.setDelayed(true);
		flightThree.setDepartureCity("Bruxelles");
		flightThree.setDestinationCity("Bucharest");
		flightThree.setDepartureDate(LocalDate.of(2019, Month.JUNE, 12));
		flightThree.setType(FlightType.INTERNATIONAL);
		flightThree.setDurationMin(150);
		flightThree.setAircraft(new Aircraft("A320", 170));
		
		FlightInformation flightFour = new FlightInformation();
		flightFour.setDelayed(true);
		flightFour.setDepartureCity("Madrid");
		flightFour.setDestinationCity("Barcelona");
		flightFour.setDepartureDate(LocalDate.of(2019, Month.JUNE, 12));
		flightFour.setType(FlightType.INTERNAL);
		flightFour.setDurationMin(120);
		flightFour.setAircraft(new Aircraft("A319", 150));
		
		FlightInformation flightFive = new FlightInformation();
		flightFive.setDelayed(false);
		flightFive.setDepartureCity("Las Vegas");
		flightFive.setDestinationCity("Washington");
		flightFive.setDepartureDate(LocalDate.of(2019, Month.JUNE, 10));
		flightFive.setType(FlightType.INTERNAL);
		flightFive.setDurationMin(400);
		flightFour.setAircraft(new Aircraft("A319", 150));
		flightFive.setDescription("Flight from LA to Washington via Paris");
		
		FlightInformation flightSix = new FlightInformation();
		flightSix.setDelayed(false);
		flightSix.setDepartureCity("Bucharest");
		flightSix.setDestinationCity("Rome");
		flightSix.setDepartureDate(LocalDate.of(2019, Month.JUNE, 13));
		flightSix.setType(FlightType.INTERNATIONAL);
		flightSix.setDurationMin(110);
		flightSix.setAircraft(new Aircraft("A321 Neo", 200));
		
		List<FlightInformation> flightsToSave = List.of(flightOne, flightTwo, flightThree, flightFour, flightFive, flightSix);
		flightInformationRepository.insert(flightsToSave);
		logger.info("Multiple Flights inserted using ApplicationRunner and Repository.....");
		
		long count = flightInformationRepository.count();
		logger.info("Total available records: {}", count);
		
		ObjectMapper mapper = new ObjectMapper();
		
		List<FlightInformation> flightsList = flightInformationRepository.findAll(Sort.by("departure").ascending());
		String flightDetails = flightsList.stream().map(eachFlight -> {
			try {
				return mapper.writeValueAsString(eachFlight);
			} catch (JsonProcessingException exception) {
				logger.error("Error in JsonProcessing", exception);
				return eachFlight.toString();
			}
		}).collect(Collectors.joining(","));
		logger.info("Found records: {}", flightDetails);
		
	}

}
