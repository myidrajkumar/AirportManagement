/**
 * 
 */
package com.rajkumar.mongodb.domain;

/**
 * @author Rajkumar
 *
 */
public enum City {

	BANGALORE("Bangalore"),
	CHENNAI("Chennai"),
	BOSTON("Boston");
	
	private String name;
	private City(String city) {
		this.name = city;
	}
	
	public String getName() {
		return name;
	}
}
