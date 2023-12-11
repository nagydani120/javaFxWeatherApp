package com.weather_app;

import com.weather_app.weather_by_time.OneDayWeather;
import com.weather_app.weather_by_time.OneWeekWeather;

public class Model {

	public static void upgradeViewOnSearch(String location) {
		try {
			Search search = WeatherAPIConnector.getSearch(location);
			upgradeMainView(search);
			upgradeWeeklyView(search.getWeek());
			upgradeHourlyView(search.getWeek().getDays().get(0));
		} catch (IllegalLocationException e) {
			ViewFactory.showErrorMessage(e.getMessage());
		}

	}

	private static void upgradeMainView(Search search) {
		ViewFactory.getMainController().setSearch(search);
	}

	public static void upgradeWeeklyView(OneWeekWeather weekWeather) {
		ViewFactory.getWeeklyController().setWeeksWeather(weekWeather);
	}

	public static void upgradeHourlyView(OneDayWeather dayWeather) {
		ViewFactory.getHourlyController().setDayWeather(dayWeather);
	}

}
