/**
 * 
 */
package com.rajkumar.mongodb.runner;

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
		saveMultipleFlights();
		logger.info("Multiple Flights inserted.....");
		
	}

	private void saveMultipleFlights() {
		var airIndia = new Aircraft("Air India", 5);
		var spiceJet = new Aircraft("Spice Jet", 6);
		
		var bangaloreToChennaiAirIndia = new FlightInformation();
		bangaloreToChennaiAirIndia.setAircraft(airIndia);
		
		bangaloreToChennaiAirIndia.setDelayed(true);
		bangaloreToChennaiAirIndia.setDepartureCity(City.BANGALORE.getName());
		bangaloreToChennaiAirIndia.setDepartureDate(LocalDate.now());
		bangaloreToChennaiAirIndia.setDestinationCity(City.CHENNAI.getName());
		bangaloreToChennaiAirIndia.setDurationMin(80);
		bangaloreToChennaiAirIndia.setType(FlightType.INTERNAL);
		
		var bangaloreToChennaiSpiceJet = new FlightInformation();
		bangaloreToChennaiSpiceJet.setAircraft(spiceJet);
		
		bangaloreToChennaiSpiceJet.setDelayed(false);
		bangaloreToChennaiSpiceJet.setDepartureCity(City.BANGALORE.getName());
		bangaloreToChennaiSpiceJet.setDepartureDate(LocalDate.now());
		bangaloreToChennaiSpiceJet.setDestinationCity(City.CHENNAI.getName());
		bangaloreToChennaiSpiceJet.setDurationMin(60);
		bangaloreToChennaiSpiceJet.setType(FlightType.INTERNAL);
		
		
		var bangaloreToBostonAirIndia = new FlightInformation();
		bangaloreToBostonAirIndia.setAircraft(airIndia);
		
		bangaloreToBostonAirIndia.setDelayed(false);
		bangaloreToBostonAirIndia.setDepartureCity(City.BANGALORE.getName());
		bangaloreToBostonAirIndia.setDepartureDate(LocalDate.now());
		bangaloreToBostonAirIndia.setDestinationCity(City.BOSTON.getName());
		bangaloreToBostonAirIndia.setDurationMin(280);
		bangaloreToBostonAirIndia.setType(FlightType.INTERNATIONAL);
		
		var bangaloreToBostonSpiceJet = new FlightInformation();
		bangaloreToBostonSpiceJet.setAircraft(spiceJet);
		
		bangaloreToBostonSpiceJet.setDelayed(true);
		bangaloreToBostonSpiceJet.setDepartureCity(City.BANGALORE.getName());
		bangaloreToBostonSpiceJet.setDepartureDate(LocalDate.now());
		bangaloreToBostonSpiceJet.setDestinationCity(City.BOSTON.getName());
		bangaloreToBostonSpiceJet.setDurationMin(480);
		bangaloreToBostonSpiceJet.setType(FlightType.INTERNATIONAL);
		
		var chennaiToBostonSpiceJet = new FlightInformation();
		chennaiToBostonSpiceJet.setAircraft(spiceJet);
		
		chennaiToBostonSpiceJet.setDelayed(true);
		chennaiToBostonSpiceJet.setDepartureCity(City.CHENNAI.getName());
		chennaiToBostonSpiceJet.setDepartureDate(LocalDate.now());
		chennaiToBostonSpiceJet.setDestinationCity(City.BOSTON.getName());
		chennaiToBostonSpiceJet.setDurationMin(400);
		chennaiToBostonSpiceJet.setType(FlightType.INTERNATIONAL);
		
		var flightsList = List.of(bangaloreToBostonAirIndia, bangaloreToBostonSpiceJet, bangaloreToChennaiAirIndia, bangaloreToChennaiSpiceJet, chennaiToBostonSpiceJet);
		flightsList.forEach(mongoTemplate::save);
	}

}
