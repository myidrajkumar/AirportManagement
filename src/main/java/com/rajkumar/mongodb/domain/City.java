/**
 * 
 */
package com.rajkumar.mongodb.domain;

/**
 * @author Rajkumar
 *
 */
public enum City {

	Bangalore("Bangalore"),
	Chennai("Chennai"),
	Boston("Boston");
	
	private String name;
	private City(String city) {
		this.name = city;
	}
	
	public String getName() {
		return name;
	}
}
