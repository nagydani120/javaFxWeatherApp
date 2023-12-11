package com.weather_app.controllers;

import java.net.URL;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.Locale;
import java.util.ResourceBundle;

import com.weather_app.Model;
import com.weather_app.Search;
import com.weather_app.enums.Moonphase;
import com.weather_app.weather_by_time.OneDayWeather;
import com.weather_app.weather_by_time.OneHourWeather;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class MainController implements Initializable {

	private OneHourWeather hourWeather;
	private OneDayWeather showingWeather;
	private Search search;
	private Timeline timeline;

	@FXML
	private Text actualTemp, uvIndexValue, windSpeedValue, humidityValue, pressureValue, sunriseTime, sunsetTime,
			moonphaseValue, moonphaseText, location, dayOfWeek, time, date, timezone;
	@FXML
	private ImageView actualWeatherIcon;
	@FXML
	private TextField searchBar;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		searchBar.setOnKeyPressed(k -> {
			if (k.getCode() == KeyCode.ENTER) {
				search();
			}
		});
	}

	@FXML
	private void search() {
		String locationToSearch = searchBar.getText().replaceAll(" ", "_");

		if (!locationToSearch.isEmpty()) {
			Model.upgradeViewOnSearch(locationToSearch);
			searchBar.setText("");
		}

	}

	public void setShowingWeather(OneHourWeather weather) {

	}

	public void setSearch(Search search) {
		this.search = search;
		showingWeather = search.getFirstDay();
		hourWeather = search.getWeek().getDays().get(0).getHours()
				.get(LocalTime.now(ZoneId.of("Europe/London")).plusHours(search.getTimeZoneOffset()).getHour()); // temporally
																													// the
																													// actual
																													// days
		updateView();
	}

	private void updateView() {
		actualTemp.setText(hourWeather.getTemp().getValueCelsius());
		uvIndexValue.setText(String.valueOf(hourWeather.getUvIndex()));
		windSpeedValue.setText(hourWeather.getWindSpeed() + "km/h");
		humidityValue.setText(hourWeather.getHumidity() + "%");
		pressureValue.setText(String.valueOf(hourWeather.getPressure()));
		sunriseTime.setText(showingWeather.getSunrise().toString());
		sunsetTime.setText(showingWeather.getSunset().toString());
		moonphaseValue.setText(String.valueOf(showingWeather.getPhase()));
		moonphaseText.setText(Moonphase.getMoonphase(showingWeather.getPhase()).getMoonphaseText());
		location.setText(search.getResolvedAddress());
		dayOfWeek.setText(showingWeather.getDate().getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.getDefault()));

		
		date.setText(showingWeather.getDate().toString());
		timezone.setText(search.getTimeZone());
		setClock();

		Image image = new Image(getClass().getResource(hourWeather.getCondition().getIconPath()).toExternalForm());
		actualWeatherIcon.setImage(image);
	}

	private void setClock() {
		time.setText(hourWeather.getTime().toString());
		DateTimeFormatter pattern = DateTimeFormatter.ofPattern("HH:mm");
		KeyFrame frame = new KeyFrame(Duration.seconds(1), e -> {
			LocalTime locationsTime = LocalTime.now(ZoneId.of("Europe/London")).plusHours(search.getTimeZoneOffset());
			time.setText(pattern.format(locationsTime));
		});
		timeline = new Timeline(frame);
		timeline.setCycleCount(Timeline.INDEFINITE);
		timeline.play();

	}

}
