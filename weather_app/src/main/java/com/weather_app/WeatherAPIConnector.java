package com.weather_app;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

import org.json.JSONObject;

public class WeatherAPIConnector {

	private static final String API_KEY = "key=YGKGES5MQQACXJYER7BJVMB6D";
	private static final String URL = "https://weather.visualcrossing.com/VisualCrossingWebServices/rest/services/timeline/";
	private static final String CONTENT_TYPE = "contentType=json";
	private static final String UNIT_GROUP = "unitGroup=metric";

	public static JSONObject getJsonResult(String searchLocation) throws IOException, InterruptedException {
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest req = HttpRequest
				.newBuilder(URI.create(URL +searchLocation+ "?" + UNIT_GROUP + "&" + API_KEY + "&" + CONTENT_TYPE)).build();
		HttpResponse<String> resp = client.send(req, BodyHandlers.ofString());
		return new JSONObject(resp.body());
	}

	
	
}
