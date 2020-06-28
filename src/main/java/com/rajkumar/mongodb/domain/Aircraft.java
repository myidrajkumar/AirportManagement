/**
 * 
 */
package com.rajkumar.mongodb.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author Rajkumar
 *
 */
@Data
@AllArgsConstructor
public class Aircraft {

	private String model;
	private int nbSeats;
	
}
