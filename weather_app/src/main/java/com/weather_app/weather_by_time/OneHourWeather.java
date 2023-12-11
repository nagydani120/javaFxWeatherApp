package com.weather_app.weather_by_time;

import java.time.LocalTime;

import com.weather_app.Temperature;
import com.weather_app.enums.Condition;

public class OneHourWeather {

	private LocalTime time;
	private Temperature temp;
	private Condition condition;
	private double humidity;
	private double windSpeed;
	private int pressure;
	private int uvIndex;

	public OneHourWeather(LocalTime time, Temperature temp, Condition condition, double humidity, double windSpeed,
			int pressure, int uvIndex) {
		this.time = time;
		this.temp = temp;
		this.condition = condition;
		this.humidity = humidity;
		this.windSpeed = windSpeed;
		this.pressure = pressure;
		this.uvIndex = uvIndex;
	}

	public LocalTime getTime() {
		return time;
	}

	public void setTime(LocalTime time) {
		this.time = time;
	}

	public Temperature getTemp() {
		return temp;
	}

	public void setTemp(Temperature temp) {
		this.temp = temp;
	}

	public Condition getCondition() {
		return condition;
	}

	public void setCondition(Condition condition) {
		this.condition = condition;
	}

	public double getHumidityDouble() {
		return humidity;
	}

	public int getHumidity() {
		return (int) Math.round(humidity);
	}

	public void setHumidity(double humidity) {
		this.humidity = humidity;
	}

	public double getWindSpeed() {
		return windSpeed;
	}

	public void setWindSpeed(double windSpeed) {
		this.windSpeed = windSpeed;
	}

	public int getPressure() {
		return pressure;
	}

	public void setPressure(int pressure) {
		this.pressure = pressure;
	}

	public int getUvIndex() {
		return uvIndex;
	}

	public void setUvIndex(int uvIndex) {
		this.uvIndex = uvIndex;
	}

	@Override
	public String toString() {
		return "OneHourWeather [time=" + time + ", temp=" + temp + ", condition=" + condition + ", humidity=" + humidity
				+ ", windSpeed=" + windSpeed + ", pressure=" + pressure + ", uvIndex=" + uvIndex + "]";
	}
}
