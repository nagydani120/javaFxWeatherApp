package com.weather_app;

public enum Moonphase {

	NEW("New moon"), WAXING_CRESCENT("Waxing crescent moon"), FIRST_QUARTER("First quarter moon"),
	WAXING_GIBBOUS("Waxing gibbous moon"), FULL("Full moon"), WANING_GIBBOUS("Waning gibbous moon"),
	LAST_QUARTER("Last quarter moon"), WANING_CRESCENT("Waning crescent moon");

	private String name;

	private Moonphase(String name) {
		this.name = name;
	}

	public static Moonphase getMoonphase(double moonPhaseValue) {
		if (moonPhaseValue < 12.5) {
			return Moonphase.NEW;
		} else if (moonPhaseValue < 25) {
			return Moonphase.WAXING_CRESCENT;
		} else if (moonPhaseValue < 37.5) {
			return Moonphase.FIRST_QUARTER;
		} else if (moonPhaseValue < 50) {
			return Moonphase.WAXING_CRESCENT;
		} else if (moonPhaseValue < 62.5) {
			return Moonphase.FULL;
		} else if (moonPhaseValue < 75) {
			return Moonphase.WANING_GIBBOUS;
		} else if (moonPhaseValue < 87.5) {
			return Moonphase.LAST_QUARTER;
		} else {
			return Moonphase.WANING_CRESCENT;
		}

	}

	public String getMoonphaseText() {
		return name;
	}
}
