/**
 * 
 */
package com.rajkumar.mongodb;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.rajkumar.mongodb.domain.FlightInformation;
import com.rajkumar.mongodb.domain.FlightType;
import com.rajkumar.mongodb.queries.FlightInformationQueries;

/**
 * @author Rajkumar
 *
 */
@Component
@Order(2)
public class ApplicationDataQueryRunner implements CommandLineRunner {

	static final Logger logger = LogManager.getLogger(ApplicationDataQueryRunner.class);

	private FlightInformationQueries flightInformationQueries;

	public ApplicationDataQueryRunner(FlightInformationQueries flightInformationQueries) {
		this.flightInformationQueries = flightInformationQueries;
	}

	@Override
	public void run(String... args) throws Exception {
		String formattedLine = "*".repeat(80);
		queryingAllWithDepartureFieldOrdered();
		logger.info(formattedLine);
		queryOnFieldId();
		logger.info(formattedLine);
		queryCountOfInternationalFlights();
		logger.info(formattedLine);
		queryForDepartureFiled();
		logger.info(formattedLine);
		queryForInBetweenDuration();
		logger.info(formattedLine);
		queryForDelayedDeparture();
		logger.info(formattedLine);
		queryForCityWithNotDelayed();
		logger.info(formattedLine);
		queryForInnerDocumentAircraftModel();
		logger.info(formattedLine);
	}

	/**
	 * Currently do not know how to not to insert the field when it is null.
	 * This find method even returns when field is null. 
	 */
	private void queryingAllWithDepartureFieldOrdered() {
		queryAllOnDepartureFieldOrdered(0, 1);
		queryAllOnDepartureFieldOrdered(0, 2);
		queryAllOnDepartureFieldOrdered(0, 10);
		queryAllOnDepartureFieldOrdered(1, 2);
	}

	/**
	 * Based on PageSize, the query is built and that many records are returned from the pageNo.
	 * If totalRecords=6 and pageSize=1, then there will be 6 pages and if pageNo=2, then only 3rd will be returned.
	 * If totalRecords=6 and pageSize=3, then there will be 2 pages and if pageNo=0, then only first 3 rows will be returned
	 */
	private void queryAllOnDepartureFieldOrdered(int pageNo, int pageSize) {
		List<FlightInformation> flights = flightInformationQueries.findAll("departure", pageNo, pageSize);
		flights.forEach(logger::info);
		logger.info("Multiple Flights based on 'departure' field asecending order Queried.....");
	}
	
	private void queryOnFieldId() {
		List<FlightInformation> flights = flightInformationQueries.findAll();
		FlightInformation firstFlight = flightInformationQueries.findSingleById(flights.get(0).getId());
		logger.info(firstFlight);
		logger.info("First Flight based on 'ID' field Queried.....");
	}
	
	private void queryCountOfInternationalFlights() {
		long count = flightInformationQueries.countInternationalFlights(FlightType.INTERNATIONAL);
		logger.info(count);
		logger.info("International Flights clount is queried.....");
	}
	
	private void queryForDepartureFiled() {
		List<FlightInformation> flights = flightInformationQueries.findForFiled("departure", "Bangalore");
		flights.forEach(logger::info);
		logger.info("Flights based on 'departure' field of 'Bangalore' Queried.....");
	}
	
	private void queryForInBetweenDuration() {
		List<FlightInformation> flights = flightInformationQueries.findByDuration(75, 300);
		flights.forEach(logger::info);
		logger.info("Flights in-between 'duration' field Queried.....");
	}
	
	private void queryForDelayedDeparture() {
		List<FlightInformation> flights = flightInformationQueries.findDelayedAtDeparture("Bangalore", true);
		flights.forEach(logger::info);
		logger.info("Flights with mutiple fields and condition Queried.....");
	}
	
	private void queryForCityWithNotDelayed() {
		List<FlightInformation> flights = flightInformationQueries.findRelatedToCityAndNotDelayed("Chennai");
		flights.forEach(logger::info);
		logger.info("Flights with mutiple fields or/and condition Queried.....");
	}
	
	private void queryForInnerDocumentAircraftModel() {
		List<FlightInformation> flights = flightInformationQueries.findInnerDocument("Air India");
		flights.forEach(logger::info);
		logger.info("Flights with inner document Queried.....");
	}
}
