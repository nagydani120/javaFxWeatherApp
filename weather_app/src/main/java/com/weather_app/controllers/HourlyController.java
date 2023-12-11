package com.weather_app.controllers;

import com.weather_app.weather_by_time.OneDayWeather;
import com.weather_app.weather_by_time.OneHourWeather;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

public class HourlyController {

	private OneDayWeather dayWeather;

	@FXML
	private Text time03Temp, time06Temp, time09Temp, time12Temp, time15Temp, time18Temp, time21Temp;

	@FXML
	private ImageView time03Icon, time06Icon, time09Icon, time12Icon, time15Icon, time18Icon, time21Icon;

	public void setDayWeather(OneDayWeather oneDayWeather) {
		this.dayWeather = oneDayWeather;
		updateView();
	}

	private void updateView() {
		ImageView[] imageViews = getImageViewsAsArray();
		Text[] tempTexts = getTempTextAsArray();

		for (int i = 0, hour = 3; i < imageViews.length; i++, hour += 3) {
			OneHourWeather hourWeather = dayWeather.getHours().get(hour);

			Image image = new Image(getClass().getResource(hourWeather.getCondition().getIconPath()).toExternalForm());
			imageViews[i].setImage(image);
			tempTexts[i].setText(hourWeather.getTemp().getValueCelsius());

		}
	}

	private ImageView[] getImageViewsAsArray() {
		return new ImageView[] { time03Icon, time06Icon, time09Icon, time12Icon, time15Icon, time18Icon, time21Icon };
	}

	private Text[] getTempTextAsArray() {
		return new Text[] { time03Temp, time06Temp, time09Temp, time12Temp, time15Temp, time18Temp, time21Temp };
	}
}
