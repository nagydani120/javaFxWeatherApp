package com.weather_app.controllers;

import java.time.LocalDate;
//import java.io.File;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;

import com.weather_app.Model;
import com.weather_app.weather_by_time.OneDayWeather;
import com.weather_app.weather_by_time.OneWeekWeather;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class WeeklyController {

	private OneWeekWeather weeksWeather;
	private VBox actuallySelectedVBox;

	@FXML
	private VBox day0, day1, day2, day3, day4, day5, day6;
	@FXML
	private Text day0Name, day1Name, day2Name, day3Name, day4Name, day5Name, day6Name;
	@FXML
	private ImageView day0Icon, day1Icon, day2Icon, day3Icon, day4Icon, day5Icon, day6Icon;
	@FXML
	private Text day0Min, day1Min, day2Min, day3Min, day4Min, day5Min, day6Min;
	@FXML
	private Text day0Max, day1Max, day2Max, day3Max, day4Max, day5Max, day6Max;

	public void setWeeksWeather(OneWeekWeather weeksWeather) {
		this.weeksWeather = weeksWeather;
		updateView();
		setListeners();
	}

	private void setListeners() {

		VBox[] vBoxes = getBoxesAsArray();
		for (int i = 0; i < vBoxes.length; i++) {
			vBoxes[i].setOnMouseClicked(ev -> {
				VBox selectedBox = (VBox) ev.getSource();
				animateSelection(selectedBox);
				Model.upgradeHourlyView(getSelectedDay());
			});
		}
	}

	private void updateView() {
		VBox[] boxes = getBoxesAsArray();
		ArrayList<OneDayWeather> days = weeksWeather.getDays();

		for (int i = 0; i < boxes.length; i++) {
			OneDayWeather oneDayWeather = days.get(i);
			ObservableList<Node> childrens = boxes[i].getChildren();

			Text dayText = (Text) childrens.get(0);
			ImageView weatherIcon = (ImageView) childrens.get(1);
			HBox minMaxHbox = (HBox) childrens.get(2);
			Text minTempText = (Text) minMaxHbox.getChildren().get(0);
			Text maxTempText = (Text) minMaxHbox.getChildren().get(1);

			dayText.setText(oneDayWeather.getDate().isEqual(LocalDate.now()) ? "Today"
					: oneDayWeather.getDate().getDayOfWeek().getDisplayName(TextStyle.SHORT, Locale.getDefault()));
			Image image = new Image(
					getClass().getResource(oneDayWeather.getDailyCondition().getIconPath()).toExternalForm());
			weatherIcon.setImage(image);
			minTempText.setText(oneDayWeather.getDailyMinTemperature().getValueCelsius());
			maxTempText.setText(oneDayWeather.getDailyMaxTemperature().getValueCelsius());
		}
	}

	private void animateSelection(VBox boxToSelect) {
		if (actuallySelectedVBox != null) {
			actuallySelectedVBox.getStyleClass().removeAll("bordered");
		}
		boxToSelect.getStyleClass().add("bordered");
		actuallySelectedVBox = boxToSelect;
	}

	private VBox[] getBoxesAsArray() {
		return new VBox[] { day0, day1, day2, day3, day4, day5, day6 };
	}

	private OneDayWeather getSelectedDay() {
		int index = Arrays.asList(getBoxesAsArray()).indexOf(actuallySelectedVBox);
		return weeksWeather.getDays().get(index);
	}
}
