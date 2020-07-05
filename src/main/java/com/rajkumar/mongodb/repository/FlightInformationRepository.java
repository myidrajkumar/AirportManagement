package com.rajkumar.mongodb.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.rajkumar.mongodb.domain.FlightInformation;

@Repository
public interface FlightInformationRepository extends MongoRepository<FlightInformation, String> {

}
