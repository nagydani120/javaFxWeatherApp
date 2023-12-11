module com.weather_app {
	requires javafx.controls;
	requires javafx.fxml;
	requires java.net.http;
	requires org.json;

	opens com.weather_app to javafx.fxml;
	opens com.weather_app.controllers to javafx.fxml;

	exports com.weather_app;
	exports com.weather_app.controllers;
	exports com.weather_app.weather_by_time;
	exports com.weather_app.enums;
}