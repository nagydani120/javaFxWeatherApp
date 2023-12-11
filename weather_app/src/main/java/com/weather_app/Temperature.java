package com.weather_app;

public class Temperature {

	private final static String DEGREE_MARK = "°C";
	private double value;

	public Temperature(double value) {
		this.value = value;
	}

	public double getValue() {
		return value;
	}

	public String getValueCelsius() {
		return Math.round(value) + DEGREE_MARK;
	}

	public void setValue(double value) {
		this.value = value;
	}
}
