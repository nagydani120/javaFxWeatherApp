package com.weather_app;

public enum Condition {

	SNOW("snow.png"), RAIN("rain.png"), FOG("fog.png"), WIND("wind.png"), CLOUDY("cloudy.png"), PARTLY_CLOUDY_DAY("partly_cloudy_day.png"),
	PARTLY_CLOUDY_NIGHT("partly_cloudy_night.png"), CLEAR_DAY("clear_day.png"), CLEAR_NIGHT("clear_night.png");

	private final String iconFileName;
	private final String filePath = "/com/weather_app/resources/images/icons/weather_icons/";

	private Condition(String filePath) {
		this.iconFileName = filePath;
	}

	public static Condition getCondition(String conditionIconName) {
		switch (conditionIconName) {
		case "snow":
			return SNOW;
		case "rain":
			return RAIN;
		case "fog":
			return FOG;
		case "wind":
			return WIND;
		case "cloudy":
			return CLOUDY;
		case "partly_cloudy_night":
			return PARTLY_CLOUDY_NIGHT;
		case "partly_cloudy_day":
			return PARTLY_CLOUDY_DAY;
		case "clear_day":
			return CLEAR_DAY;
		case "clear_night":
			return CLEAR_NIGHT;
		default:
			return null;
		}
	}

}
