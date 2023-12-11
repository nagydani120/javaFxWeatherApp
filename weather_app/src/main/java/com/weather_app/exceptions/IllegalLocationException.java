package com.weather_app.exceptions;

public class IllegalLocationException extends Exception {

	private String location;
	private static final long serialVersionUID = 1L;

	public IllegalLocationException(String location) {
		this.location = location;
	}

	@Override
	public String getMessage() {
		return "Invalid location: " + location;
	}

}
