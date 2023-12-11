package com.weather_app;

public class NoResponseException extends Exception {

	private static final long serialVersionUID = 1L;

	@Override
	public String getMessage() {
		return "No internet connection or service doesn't exist!";
	}
	
}
