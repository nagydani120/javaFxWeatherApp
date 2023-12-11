package com.weather_app;

import java.io.IOException;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * JavaFX App
 */
public class App extends Application {

	@Override
	public void start(Stage stage) throws IOException {

		ViewFactory.getView();
		Model.upgradeViewOnSearch("bratislava");
	}

	public static void main(String[] args) {
		launch();

	}

}