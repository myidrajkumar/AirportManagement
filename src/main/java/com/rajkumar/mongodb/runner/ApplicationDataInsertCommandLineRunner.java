/**
 * 
 */
package com.rajkumar.mongodb.runner;

import java.time.LocalDate;
import java.util.List;

import javax.annotation.Priority;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import com.rajkumar.mongodb.domain.Aircraft;
import com.rajkumar.mongodb.domain.FlightInformation;
import com.rajkumar.mongodb.domain.FlightType;

/**
 * @author Rajkumar
 *
 */
@Component
@Priority(2)
public class ApplicationDataInsertCommandLineRunner implements CommandLineRunner {

	static final Logger logger = LogManager.getLogger(ApplicationDataInsertCommandLineRunner.class);
	
	private MongoTemplate mongoTemplate;
	
	public ApplicationDataInsertCommandLineRunner(MongoTemplate mongoTemplate) {
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
		bangaloreToChennaiAirIndia.setDepartureCity("Bangalore");
		bangaloreToChennaiAirIndia.setDepartureDate(LocalDate.now());
		bangaloreToChennaiAirIndia.setDestinationCity("Chennai");
		bangaloreToChennaiAirIndia.setDurationMin(80);
		bangaloreToChennaiAirIndia.setType(FlightType.INTERNAL);
		
		var bangaloreToChennaiSpiceJet = new FlightInformation();
		bangaloreToChennaiSpiceJet.setAircraft(spiceJet);
		
		bangaloreToChennaiSpiceJet.setDelayed(false);
		bangaloreToChennaiSpiceJet.setDepartureCity("Bangalore");
		bangaloreToChennaiSpiceJet.setDepartureDate(LocalDate.now());
		bangaloreToChennaiSpiceJet.setDestinationCity("Chennai");
		bangaloreToChennaiSpiceJet.setDurationMin(60);
		bangaloreToChennaiSpiceJet.setType(FlightType.INTERNAL);
		
		
		var bangaloreToBostonAirIndia = new FlightInformation();
		bangaloreToBostonAirIndia.setAircraft(airIndia);
		
		bangaloreToBostonAirIndia.setDelayed(false);
		bangaloreToBostonAirIndia.setDepartureCity("Bangalore");
		bangaloreToBostonAirIndia.setDepartureDate(LocalDate.now());
		bangaloreToBostonAirIndia.setDestinationCity("Boston");
		bangaloreToBostonAirIndia.setDurationMin(280);
		bangaloreToBostonAirIndia.setType(FlightType.INTERNATIONAL);
		
		var bangaloreToBostonSpiceJet = new FlightInformation();
		bangaloreToBostonSpiceJet.setAircraft(spiceJet);
		
		bangaloreToBostonSpiceJet.setDelayed(true);
		bangaloreToBostonSpiceJet.setDepartureCity("Bangalore");
		bangaloreToBostonSpiceJet.setDepartureDate(LocalDate.now());
		bangaloreToBostonSpiceJet.setDestinationCity("Boston");
		bangaloreToBostonSpiceJet.setDurationMin(480);
		bangaloreToBostonSpiceJet.setType(FlightType.INTERNATIONAL);
		
		var chennaiToBostonSpiceJet = new FlightInformation();
		chennaiToBostonSpiceJet.setAircraft(spiceJet);
		
		chennaiToBostonSpiceJet.setDelayed(true);
		chennaiToBostonSpiceJet.setDepartureCity("Chennai");
		chennaiToBostonSpiceJet.setDepartureDate(LocalDate.now());
		chennaiToBostonSpiceJet.setDestinationCity("Boston");
		chennaiToBostonSpiceJet.setDurationMin(400);
		chennaiToBostonSpiceJet.setType(FlightType.INTERNATIONAL);
		
		var flightsList = List.of(bangaloreToBostonAirIndia, bangaloreToBostonSpiceJet, bangaloreToChennaiAirIndia, bangaloreToChennaiSpiceJet, chennaiToBostonSpiceJet);
		
		/**
		 * 'Save' is used here but not the correct approach
		 * 
		 * 'Insert' --> Inserts single doc and throws error if 'id' already exists
		 * 'InsertMany' --> Inserts multiple documents
		 * 'Save' --> If 'Id' is not present, inserts else completely replaces with new document
		 * 'Update' --> Updates only the fields in the document
		 * 'UpdateMulti' --> Updates multiple documents in the collection
		 */
		flightsList.forEach(mongoTemplate::save);
	}

}
