package com.weather_app;

import java.io.IOException;

import com.weather_app.controllers.HourlyController;
import com.weather_app.controllers.MainController;
import com.weather_app.controllers.WeeklyController;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class ViewFactory {

	private static MainController mainController;
	private static HourlyController hourlyController;
	private static WeeklyController weeklyController;

	private ViewFactory() {
	}

	public static void getView() {
		if (mainController == null) {
			mainController = new MainController();
			hourlyController = new HourlyController();
			weeklyController = new WeeklyController();
		}

		Stage stage = new Stage();
		FXMLLoader top = new FXMLLoader(ViewFactory.class.getResource("/com/weather_app/resources/top.fxml"));
		FXMLLoader bottom = new FXMLLoader(ViewFactory.class.getResource("/com/weather_app/resources/bottom.fxml"));
		FXMLLoader main = new FXMLLoader(ViewFactory.class.getResource("/com/weather_app/resources/center.fxml"));

		top.setController(weeklyController);
		bottom.setController(hourlyController);
		main.setController(mainController);

		try {

			BorderPane pane = main.load();
			HBox topHbox = top.load();
			HBox bottomHbox = bottom.load();

			pane.setTop(topHbox);
			pane.setBottom(bottomHbox);

			Scene scene = new Scene(pane);
			setCenterBackground(pane);		
			setStyleSheet(scene);
			stage.setScene(scene);
	
		} catch (IOException e) {
			e.printStackTrace();
		}
		stage.setResizable(false);
		stage.show();
	
	}

	public static MainController getMainController() {
		return mainController;
	}

	public static HourlyController getHourlyController() {
		return hourlyController;
	}

	public static WeeklyController getWeeklyController() {
		return weeklyController;
	}

	public static void showErrorMessage(String message) {
		Alert alert = new Alert(AlertType.WARNING, message, ButtonType.OK);
		alert.showAndWait();
	}

	private static void setStyleSheet(Scene scene) {
		String cssPath = ViewFactory.class.getResource("resources/style.css").toExternalForm();
		scene.getStylesheets().add(cssPath);
	}

	private static void setCenterBackground(BorderPane pane) {
		Image image = new Image(ViewFactory.class.getResource("/com/weather_app/resources/images/backgrounds/day.jpg").toExternalForm());
		BackgroundImage img = new BackgroundImage(image, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT,
				BackgroundPosition.DEFAULT, new BackgroundSize(0, 0, false, false, false, true));
		pane.setBackground(new Background(img));
	}

}
