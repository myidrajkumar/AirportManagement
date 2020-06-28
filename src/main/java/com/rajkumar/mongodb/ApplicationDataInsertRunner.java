/**
 * 
 */
package com.rajkumar.mongodb;

import java.time.LocalDate;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import com.rajkumar.mongodb.domain.Aircraft;
import com.rajkumar.mongodb.domain.City;
import com.rajkumar.mongodb.domain.FlightInformation;
import com.rajkumar.mongodb.domain.FlightType;

/**
 * @author Rajkumar
 *
 */
@Component
@Order(1)
public class ApplicationDataInsertRunner implements CommandLineRunner {

	static final Logger logger = LogManager.getLogger(ApplicationDataInsertRunner.class);
	
	private MongoTemplate mongoTemplate;
	
	public ApplicationDataInsertRunner(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}
	
	@Override
	public void run(String... args) throws Exception {
//		saveEmptyFlight();
		saveMultipleFlights();
		logger.info("Multiple Flights inserted.....");
		
	}

	private void saveEmptyFlight() {
		FlightInformation emptyFlight = new FlightInformation();
		this.mongoTemplate.save(emptyFlight);
	}
	
	private void saveMultipleFlights() {
		var airIndia = new Aircraft("Air India", 5);
		var spiceJet = new Aircraft("Spice Jet", 6);
		
		var bangaloreToChennaiAirIndia = new FlightInformation();
		bangaloreToChennaiAirIndia.setAircraft(airIndia);
		
		bangaloreToChennaiAirIndia.setDelayed(true);
		bangaloreToChennaiAirIndia.setDepartureCity(City.Bangalore.getName());
		bangaloreToChennaiAirIndia.setDepartureDate(LocalDate.now());
		bangaloreToChennaiAirIndia.setDestinationCity(City.Chennai.getName());
		bangaloreToChennaiAirIndia.setDurationMin(80);
		bangaloreToChennaiAirIndia.setType(FlightType.INTERNAL);
		
		var bangaloreToChennaiSpiceJet = new FlightInformation();
		bangaloreToChennaiSpiceJet.setAircraft(spiceJet);
		
		bangaloreToChennaiSpiceJet.setDelayed(false);
		bangaloreToChennaiSpiceJet.setDepartureCity(City.Bangalore.getName());
		bangaloreToChennaiSpiceJet.setDepartureDate(LocalDate.now());
		bangaloreToChennaiSpiceJet.setDestinationCity(City.Chennai.getName());
		bangaloreToChennaiSpiceJet.setDurationMin(60);
		bangaloreToChennaiSpiceJet.setType(FlightType.INTERNAL);
		
		
		var bangaloreToBostonAirIndia = new FlightInformation();
		bangaloreToBostonAirIndia.setAircraft(airIndia);
		
		bangaloreToBostonAirIndia.setDelayed(false);
		bangaloreToBostonAirIndia.setDepartureCity(City.Bangalore.getName());
		bangaloreToBostonAirIndia.setDepartureDate(LocalDate.now());
		bangaloreToBostonAirIndia.setDestinationCity(City.Boston.getName());
		bangaloreToBostonAirIndia.setDurationMin(280);
		bangaloreToBostonAirIndia.setType(FlightType.INTERNATIONAL);
		
		var bangaloreToBostonSpiceJet = new FlightInformation();
		bangaloreToBostonSpiceJet.setAircraft(spiceJet);
		
		bangaloreToBostonSpiceJet.setDelayed(true);
		bangaloreToBostonSpiceJet.setDepartureCity(City.Bangalore.getName());
		bangaloreToBostonSpiceJet.setDepartureDate(LocalDate.now());
		bangaloreToBostonSpiceJet.setDestinationCity(City.Boston.getName());
		bangaloreToBostonSpiceJet.setDurationMin(480);
		bangaloreToBostonSpiceJet.setType(FlightType.INTERNATIONAL);
		
		var chennaiToBostonSpiceJet = new FlightInformation();
		chennaiToBostonSpiceJet.setAircraft(spiceJet);
		
		chennaiToBostonSpiceJet.setDelayed(true);
		chennaiToBostonSpiceJet.setDepartureCity(City.Chennai.getName());
		chennaiToBostonSpiceJet.setDepartureDate(LocalDate.now());
		chennaiToBostonSpiceJet.setDestinationCity(City.Boston.getName());
		chennaiToBostonSpiceJet.setDurationMin(400);
		chennaiToBostonSpiceJet.setType(FlightType.INTERNATIONAL);
		
		var flightsList = List.of(bangaloreToBostonAirIndia, bangaloreToBostonSpiceJet, bangaloreToChennaiAirIndia, bangaloreToChennaiSpiceJet, chennaiToBostonSpiceJet);
		flightsList.forEach(mongoTemplate::save);
	}

}
