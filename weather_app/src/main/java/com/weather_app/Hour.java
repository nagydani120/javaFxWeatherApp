package com.weather_app;

import java.time.LocalTime;

import javafx.concurrent.Service;
import javafx.concurrent.Task;

public class Hour extends Service<String>{

	private static LocalTime time;

	@Override
	protected Task<String> createTask() {
		
		return null;
	}
	
}
