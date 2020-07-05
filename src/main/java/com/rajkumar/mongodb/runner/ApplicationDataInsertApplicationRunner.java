package com.rajkumar.mongodb.runner;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import javax.annotation.Priority;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import com.rajkumar.mongodb.domain.Aircraft;
import com.rajkumar.mongodb.domain.FlightInformation;
import com.rajkumar.mongodb.domain.FlightType;

/**
 * 
 * What are the differences between command line runner and application runner?
 * CommandLineRunner just takes all input arguments as arguments
 * ApplicationRunner differentiates with --foo=arg1 against arg2
 * --foo=arg1 is considered as OptionArguments and 'key and value' can be retrieved as well
 * arg2 is considered as NonOptionArgument
 * Both Option and NonOption can be retrieved also as 'SourceArgs' 
 * 
 * @author Rajkumar
 *
 */
@Component
@Priority(4)
public class ApplicationDataInsertApplicationRunner implements ApplicationRunner {

	static final Logger logger = LogManager.getLogger(ApplicationDataInsertApplicationRunner.class);
	
    private MongoTemplate mongoTemplate;
	
	public ApplicationDataInsertApplicationRunner(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
		removeAllTheFlights();
		insertMultipleFlights();
	}

	private void removeAllTheFlights() {
		mongoTemplate.remove(new Query(), FlightInformation.class);
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
		
		/**
		 * To insert the documents, naive approach
		 * 
		 * mongoTemplate.insert(flightOne);
		 * mongoTemplate.insert(flightTwo);
		 */
		
		List<FlightInformation> flightsToSave = List.of(flightOne, flightTwo, flightThree, flightFour, flightFive, flightSix);
		mongoTemplate.insertAll(flightsToSave);
		logger.info("Multiple Flights inserted using ApplicationRunner.....");
		
	}

}
