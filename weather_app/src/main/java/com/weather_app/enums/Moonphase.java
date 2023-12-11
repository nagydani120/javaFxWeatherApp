package com.weather_app.enums;

public enum Moonphase {

	NEW("New moon"), WAXING_CRESCENT("Waxing crescent moon"), FIRST_QUARTER("First quarter moon"),
	WAXING_GIBBOUS("Waxing gibbous moon"), FULL("Full moon"), WANING_GIBBOUS("Waning gibbous moon"),
	LAST_QUARTER("Last quarter moon"), WANING_CRESCENT("Waning crescent moon");

	private String name;

	private Moonphase(String name) {
		this.name = name;
	}

	public static Moonphase getMoonphase(double moonPhaseValue) {
		if (moonPhaseValue < 0.125) {
			return Moonphase.NEW;
		} else if (moonPhaseValue < 0.25) {
			return Moonphase.WAXING_CRESCENT;
		} else if (moonPhaseValue < 0.375) {
			return Moonphase.FIRST_QUARTER;
		} else if (moonPhaseValue < 0.50) {
			return Moonphase.WAXING_CRESCENT;
		} else if (moonPhaseValue < 0.625) {
			return Moonphase.FULL;
		} else if (moonPhaseValue < 0.75) {
			return Moonphase.WANING_GIBBOUS;
		} else if (moonPhaseValue < 0.875) {
			return Moonphase.LAST_QUARTER;
		} else {
			return Moonphase.WANING_CRESCENT;
		}

	}

	public String getMoonphaseText() {
		return name;
	}
}
