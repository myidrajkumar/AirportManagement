package com.rajkumar.mongodb.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.mongodb.core.convert.MongoCustomConversions;

import com.rajkumar.mongodb.converter.AircraftDBReadConverter;
import com.rajkumar.mongodb.converter.AircraftDBWriteConverter;

@Configuration
public class MongoDBConverterConfiguration {

	@Bean
	public MongoCustomConversions customConversions() {
		List<Converter<?, ?>> converters = List.of(
				new AircraftDBReadConverter(),
				new AircraftDBWriteConverter()
//				,
//				new CityDBReadConverter(),
//				new CityDBWriteConverter()
				);
		return new MongoCustomConversions(converters);
	}
}
