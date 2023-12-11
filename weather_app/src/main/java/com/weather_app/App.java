package com.weather_app;

import java.io.IOException;

import javafx.application.Application;
import javafx.stage.Stage;

public class App extends Application {

	@Override
	public void start(Stage stage) throws IOException {

		ViewFactory.getView();
		Model.upgradeViewOnSearch(WeatherAPIConnector.DEFAULT_LOCATION);
	}

	@Override
	public void init() throws Exception {
	}

	public static void main(String[] args) {
		launch();

	}

}