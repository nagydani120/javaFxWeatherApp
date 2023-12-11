package com.weather_app;

import com.weather_app.weather_by_time.OneDayWeather;
import com.weather_app.weather_by_time.OneWeekWeather;

public class Search {

	private OneWeekWeather week;
	private String resolvedAddress;
	private String timeZone;
	private int timeZoneOffset;

	public Search() {
	}

	public OneDayWeather getFirstDay() {
		return week.getDays().get(0);
	}

	public OneWeekWeather getWeek() {
		return week;
	}

	public void setWeek(OneWeekWeather week) {
		this.week = week;
	}

	public String getResolvedAddress() {
		return resolvedAddress;
	}

	public void setResolvedAddress(String resolvedAddress) {
		this.resolvedAddress = resolvedAddress;
	}

	public String getTimeZone() {
		return timeZone;
	}

	public void setTimeZone(String timeZone) {
		this.timeZone = timeZone;
	}

	public int getTimeZoneOffset() {
		return timeZoneOffset;
	}

	public void setTimeZoneOffset(int timeZoneOffset) {
		this.timeZoneOffset = timeZoneOffset;
	}

	@Override
	public String toString() {
		return new StringBuilder().append("Resolved address: " + resolvedAddress).append("\n")
				.append("Time Zone: " + timeZone).append("\n").append("Time Zone Offset:" + timeZoneOffset).toString();
	}

}
