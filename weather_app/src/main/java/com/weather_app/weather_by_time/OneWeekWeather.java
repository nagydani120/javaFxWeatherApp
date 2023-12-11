package com.weather_app.weather_by_time;

import java.util.ArrayList;

public class OneWeekWeather {

	private ArrayList<OneDayWeather> days;
	private String weekDescription;

	
	public OneWeekWeather() {
	}
	
	public OneWeekWeather(ArrayList<OneDayWeather> days, String weekDescription) {
		this.days = days;
		this.weekDescription = weekDescription;
	}

	public ArrayList<OneDayWeather> getDays() {
		return days;
	}

	public void setDays(ArrayList<OneDayWeather> days) {
		this.days = days;
	}

	public String getWeekDescription() {
		return weekDescription;
	}

	public void setWeekDescription(String weekDescription) {
		this.weekDescription = weekDescription;
	}

}
