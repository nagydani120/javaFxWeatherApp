package com.weather_app;

import java.io.IOException;

import org.json.JSONObject;

public class MainTest {
	public static void main(String[] args) throws IOException, InterruptedException {

		JSONObject json = WeatherAPIConnector.getJsonResult("Tesmak");
		System.out.println(json.get("resolvedAddress"));
		json.getJSONArray("days").forEach(o -> System.out.println(o));
	}
}
