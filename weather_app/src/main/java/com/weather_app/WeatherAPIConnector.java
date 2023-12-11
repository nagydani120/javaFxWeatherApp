package com.weather_app;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ResourceBundle;

import org.json.JSONArray;
import org.json.JSONObject;

import com.weather_app.enums.Condition;
import com.weather_app.exceptions.IllegalLocationException;
import com.weather_app.weather_by_time.OneDayWeather;
import com.weather_app.weather_by_time.OneHourWeather;
import com.weather_app.weather_by_time.OneWeekWeather;

public class WeatherAPIConnector {
/**
 *
 */
	private static final String API_KEY;
	private static final String URL;
	private static final String CONTENT_TYPE;
	private static final String UNIT_GROUP;
	private static final String TIME;
	public static final String DEFAULT_LOCATION;

	static {
		ResourceBundle bundle = ResourceBundle.getBundle("com/weather_app/resources/api_data");
		
		
		API_KEY = bundle.getString("api_key");
		URL = bundle.getString("url");
		CONTENT_TYPE = bundle.getString("content_type");
		UNIT_GROUP = bundle.getString("unit_group");
		TIME = bundle.getString("time");
		DEFAULT_LOCATION = bundle.getString("default_location");
		// TODO : Make a .properties file to read resources
	}

	public static Search getSearch(String location) throws IllegalLocationException, NoResponseException {
		Search search = new Search();
		JSONObject result = getResult(location);

		/*
		 * Search + variables
		 */
		String resolvedAddress = result.getString("resolvedAddress");
		String timeZone = result.getString("timezone");
		int timeZoneOffset = result.getInt("tzoffset");

		search.setResolvedAddress(resolvedAddress);
		search.setTimeZone(timeZone);
		search.setTimeZoneOffset(timeZoneOffset);

		/*
		 * One week + variables
		 */
		OneWeekWeather weekWeather = new OneWeekWeather();
		String description = result.getString("description");

		JSONArray daysList = result.getJSONArray("days");
		ArrayList<OneDayWeather> weeklyWeather = getDailyWeatherFromJSON(daysList);

		weekWeather.setWeekDescription(description);
		weekWeather.setDays(weeklyWeather);
		search.setWeek(weekWeather);

		return search;

	}

	private static ArrayList<OneDayWeather> getDailyWeatherFromJSON(JSONArray days) {
		ArrayList<OneDayWeather> allDaysWeather = new ArrayList<>();
		Iterator<Object> daysIterator = days.iterator();
		while (daysIterator.hasNext()) {
			JSONObject day = (JSONObject) daysIterator.next();

			LocalDate dateTime = LocalDate.parse(day.getString("datetime"), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
			Condition dayCondition = Condition.getCondition(day.getString("icon"));
			LocalTime sunrise = LocalTime.parse(day.getString("sunrise"), DateTimeFormatter.ofPattern("HH:mm:ss"));
			LocalTime sunset = LocalTime.parse(day.getString("sunset"), DateTimeFormatter.ofPattern("HH:mm:ss"));
			double moonphase = day.getDouble("moonphase");

			JSONArray hours = day.getJSONArray("hours");

			ArrayList<OneHourWeather> hourlyWeatherFromJSON = getHourlyWeatherFromJSON(hours);
			allDaysWeather
					.add(new OneDayWeather(dateTime, hourlyWeatherFromJSON, dayCondition, sunrise, sunset, moonphase));
		}
		return allDaysWeather;
	}

	private static ArrayList<OneHourWeather> getHourlyWeatherFromJSON(JSONArray hours) {
		ArrayList<OneHourWeather> hoursList = new ArrayList<>();
		Iterator<Object> hoursIterator = hours.iterator();
		while (hoursIterator.hasNext()) {
			JSONObject hour = (JSONObject) hoursIterator.next();

			LocalTime times = LocalTime.parse(hour.getString("datetime"), DateTimeFormatter.ofPattern("HH:mm:ss"));
			Double temp = hour.getDouble("temp");
			Condition hourCondition = Condition.getCondition(hour.getString("icon"));
			Double humidity = hour.getDouble("humidity");
			Double windSpeed = hour.getDouble("windspeed");
			Integer pressure = hour.getInt("pressure");
			Integer uvIndex = hour.getInt("uvindex");

			hoursList.add(new OneHourWeather(times, new Temperature(temp), hourCondition, humidity, windSpeed, pressure,
					uvIndex));

		}
		return hoursList;
	}

	public static JSONObject getResult(String searchLocation) throws IllegalLocationException, NoResponseException {
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest req = HttpRequest
				.newBuilder(URI
						.create(URL + searchLocation +"/" + TIME + UNIT_GROUP + "&" + API_KEY + "&" + CONTENT_TYPE))
				.build();
		HttpResponse<String> resp = null;
		try {
			resp = client.send(req, BodyHandlers.ofString());
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
		if (resp == null) {
			throw new NoResponseException();
		}
		if (resp.statusCode() == 400) {
			throw new IllegalLocationException(searchLocation);
		}

		return new JSONObject(resp.body());
	}

	
}
