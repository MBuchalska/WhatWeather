package pl.martabuchalska.controller;

import pl.martabuchalska.model.Weather;

import java.time.format.DateTimeFormatter;

public class Settings {
    public static final int FIRST_FORECAST_AFTER_ONE_DAY = 7;
    public static final int TOTAL_NUMBER_OF_FORECAST_DATA = 40;

    public static final int FORECAST_DATA_PER_DAY = 7;
    public static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd-MMMM-yyyy");

//    private static Weather initialCityWeather;
//    private static Weather destinationCityWeather;
//
//    public Weather getInitialCityWeather() {
//        return initialCityWeather;
//    }
//
//    public void setInitialCityWeather(Weather initialCityWeather) {
//        this.initialCityWeather = initialCityWeather;
//    }
//
//    public Weather getDestinationCityWeather() {
//        return destinationCityWeather;
//    }
//
//    public void setDestinationCityWeather(Weather destinationCityWeather) {
//        this.destinationCityWeather = destinationCityWeather;
//    }
}
