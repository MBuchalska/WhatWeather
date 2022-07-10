package pl.martabuchalska.model;

public class Weather {
    // tu parametry pogody, przechowuje dane
    private CityData cityData;

    private WeatherData weatherData;
    // forecastData

    public Weather(CityData cityData, WeatherData weatherData) {
        this.cityData = cityData;
        this.weatherData = weatherData;
    }

    public CityData getCityData() {
        return cityData;
    }

    public WeatherData getWeatherData() {
        return weatherData;
    }
}
