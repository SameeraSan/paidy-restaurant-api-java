package com.paidy.restaurant.util;

import java.util.Random;
import java.util.UUID;

public class UtilMethods {
	
	// Generate a random integer(minutes) between 5-15
	public static int generateRandomTime() {
		Random rand = new Random();
		return rand.nextInt(11) + 5;
	}

	// Generate an unique ID for exceptions
	public static String getErrorId(){
		return UUID.randomUUID().toString().replaceAll("-", "").toLowerCase();
        
	}
}
