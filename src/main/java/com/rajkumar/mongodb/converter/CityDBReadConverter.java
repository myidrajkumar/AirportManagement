package com.rajkumar.mongodb.converter;

import java.util.Arrays;

import org.springframework.core.convert.converter.Converter;

import com.rajkumar.mongodb.domain.CityRevertingAsNotWorking;

public class CityDBReadConverter implements Converter<String, CityRevertingAsNotWorking> {

	@Override
	public CityRevertingAsNotWorking convert(String city) {
		CityRevertingAsNotWorking result = Arrays.stream(CityRevertingAsNotWorking.values()).filter(eachCity -> {
			System.out.println(eachCity.getName());
			System.out.println("Compared Against " + city + " and the result: " + eachCity.getName().equals(city));
			return eachCity.getName().equals(city);
		}).findAny().orElse(null);
		System.out.println("Final Result: " + result);
		return Arrays.stream(CityRevertingAsNotWorking.values()).filter(eachCity -> eachCity.getName().equals(city)).findAny().orElse(null);
	}

}
