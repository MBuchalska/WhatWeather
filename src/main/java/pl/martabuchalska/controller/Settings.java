package pl.martabuchalska.controller;

import pl.martabuchalska.model.Weather;

public class Settings {
    private static Weather initialCityWeather;
    private static Weather destinationCityWeather;

    public Weather getInitialCityWeather() {
        return initialCityWeather;
    }

    public void setInitialCityWeather(Weather initialCityWeather) {
        this.initialCityWeather = initialCityWeather;
    }

    public Weather getDestinationCityWeather() {
        return destinationCityWeather;
    }

    public void setDestinationCityWeather(Weather destinationCityWeather) {
        this.destinationCityWeather = destinationCityWeather;
    }
}
