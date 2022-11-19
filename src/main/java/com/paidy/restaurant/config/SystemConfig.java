package com.paidy.restaurant.config;

public class SystemConfig {
	
	/*
	 * Keep username and password as plain text since this a demo app
	 * */
	// Basic Auth
	public static String USERNAME = "paidy";
	public static String PASSWORD = "password";
	
	// Database
	public static String DB_URL = "jdbc:postgresql://localhost:5432/paidy";
	public static String DB_DRIVER = "org.postgresql.Driver";
	public static String DB_USERNAME = "postgres";
	public static String DB_PASSWORD = "password";
	public static int 	 DB_MIN_POOL_SIZE = 10;
	public static int 	 DB_MAX_POOL_SIZE = 50;
	public static int 	 DB_CON_TIMEOUT = 15000;
	
	// Status
	public static final int ST_ACTIVE = 1;
	public static final int ST_INACTIVE = 2;
	public static final int ST_RECEIVED = 3;
	public static final int ST_PREPARING = 4;
	public static final int ST_COMPLETED = 5;
	public static final int ST_DELIVERED = 6;
	public static final int ST_CANCELLED = 7;

}
