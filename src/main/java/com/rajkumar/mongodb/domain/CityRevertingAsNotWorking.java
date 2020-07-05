/**
 * 
 */
package com.rajkumar.mongodb.domain;

/**
 * @author Rajkumar
 *
 */
public enum CityRevertingAsNotWorking {

	BANGALORE("Bangalore"),
	CHENNAI("Chennai"),
	BOSTON("Boston"),
	ROME("Rome"),
	PARIS("Paris"),
	NEWYORK("New York"),
	COPENHAGEN("Copenhagen"),
	BRUXELLES("Bruxelles"),
	BUCHAREST("Bucharest"),
	MADRID("Madrid"),
	BARCELONA("Barcelona"),
	LASVEGAS("Las Vegas"),
	WASHINGTON("Washington");
	
	private String name;
	private CityRevertingAsNotWorking(String city) {
		this.name = city;
	}
	
	public String getName() {
		return name;
	}
}
