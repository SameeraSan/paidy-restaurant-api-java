package com.paidy.restaurant;

import org.glassfish.jersey.filter.LoggingFilter;
import org.glassfish.jersey.server.ResourceConfig;

import com.paidy.restaurant.security.AuthenticationFilter;

public class CustomApplication extends ResourceConfig {
	public CustomApplication() {
		packages("com.paidy.restaurant");
		register(LoggingFilter.class);
		register(AuthenticationFilter.class);
	}
}
