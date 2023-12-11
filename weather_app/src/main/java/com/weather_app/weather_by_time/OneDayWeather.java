package com.weather_app.weather_by_time;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.stream.Collectors;

import com.weather_app.Temperature;
import com.weather_app.enums.Condition;

public class OneDayWeather {

	private LocalDate date;
	private ArrayList<OneHourWeather> hours;
	private Condition dailyCondition;
	private LocalTime sunrise;
	private LocalTime sunset;
	private double phaseValue;

	public OneDayWeather(LocalDate date, ArrayList<OneHourWeather> hours, Condition dailyCondition, LocalTime sunrise,
			LocalTime sunset, double phase) {
		this.date = date;
		this.hours = hours;
		this.dailyCondition = dailyCondition;
		this.sunrise = sunrise;
		this.sunset = sunset;
		this.phaseValue = phase;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public ArrayList<OneHourWeather> getHours() {
		return hours;
	}

	public void setHours(ArrayList<OneHourWeather> hours) {
		this.hours = hours;
	}

	public Condition getDailyCondition() {
		return dailyCondition;
	}

	public void setDailyCondition(Condition dailyCondition) {
		this.dailyCondition = dailyCondition;
	}

	public LocalTime getSunrise() {
		return sunrise;
	}

	public void setSunrise(LocalTime sunrise) {
		this.sunrise = sunrise;
	}

	public LocalTime getSunset() {
		return sunset;
	}

	public void setSunset(LocalTime sunset) {
		this.sunset = sunset;
	}

	public double getPhase() {
		return phaseValue;
	}

	public void setPhase(double phaseValue) {
		this.phaseValue = phaseValue;
	}

	public Temperature getDailyMaxTemperature() {
		OneHourWeather dailyMax = hours.stream()
				.collect(Collectors.maxBy((o1, o2) -> Double.compare(o1.getTemp().getValue(), o2.getTemp().getValue())))
				.orElseThrow();
		return dailyMax.getTemp();
	}

	public Temperature getDailyMinTemperature() {
		OneHourWeather dailyMin = hours.stream()
				.collect(Collectors.minBy((o1, o2) -> Double.compare(o1.getTemp().getValue(), o2.getTemp().getValue())))
				.orElseThrow();
		return dailyMin.getTemp();
	}

	@Override
	public String toString() {
		return "OneDayWeather [date=" + date + ", hours=" + hours + ", dailyCondition=" + dailyCondition + ", sunrise="
				+ sunrise + ", sunset=" + sunset + ", phase=" + phaseValue + "]\n";
	}

}
