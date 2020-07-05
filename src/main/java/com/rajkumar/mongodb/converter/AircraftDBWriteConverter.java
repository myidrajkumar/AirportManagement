package com.rajkumar.mongodb.converter;

import org.springframework.core.convert.converter.Converter;

import com.rajkumar.mongodb.domain.Aircraft;

public class AircraftDBWriteConverter implements Converter<Aircraft, String> {

	@Override
	public String convert(Aircraft aircraft) {
		return aircraft.getModel() + "/" + aircraft.getNbSeats();
	}
}
