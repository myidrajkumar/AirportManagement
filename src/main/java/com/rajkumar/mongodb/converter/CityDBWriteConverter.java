package com.rajkumar.mongodb.converter;

import org.springframework.core.convert.converter.Converter;

import com.rajkumar.mongodb.domain.CityRevertingAsNotWorking;

public class CityDBWriteConverter implements Converter<CityRevertingAsNotWorking, String> {

	@Override
	public String convert(CityRevertingAsNotWorking city) {
		return city.getName();
	}
}
