package com.weather_app;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class OneDayWeather {

	private ArrayList<OneHourWeather> hours;
	private Condition dailyCondition;
	private LocalTime sunrise;
	private LocalTime sunset;
	private Moonphase phase;

	
	
	public Temperature getDailyMaxTemperature() {
		OneHourWeather dailyMax = hours.stream()
				.collect(Collectors.maxBy(
						(o1, o2) -> Double.compare(o1.getTemperature().getValue(), o2.getTemperature().getValue())))
				.orElseThrow();
		return dailyMax.getTemperature();
	}

	public Temperature getDailyMinTemperature() {
		OneHourWeather dailyMin = hours.stream()
				.collect(Collectors.minBy(
						(o1, o2) -> Double.compare(o1.getTemperature().getValue(), o2.getTemperature().getValue())))
				.orElseThrow();
		return dailyMin.getTemperature();
	}

}
