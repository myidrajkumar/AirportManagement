package com.rajkumar.mongodb.converter;

import org.springframework.core.convert.converter.Converter;

import com.rajkumar.mongodb.domain.Aircraft;

public class AircraftDBReadConverter implements Converter<String, Aircraft>{

	@Override
	public Aircraft convert(String aircraft) {
		if(aircraft == null) {
			return null;
		}
		
		String[] parts = aircraft.split("/");
		return new Aircraft(parts[0], Integer.valueOf(parts[1]));
	}

}
