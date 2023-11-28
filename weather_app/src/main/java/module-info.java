module com.weather_app {
	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.web;
	requires java.net.http;
	requires org.json;

	opens com.weather_app to javafx.fxml;
	opens com.weather_app.controllers to javafx.fxml;

	exports com.weather_app;
}