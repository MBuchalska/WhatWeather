package pl.martabuchalska.model;

public class Weather {
    // tu parametry pogody, przechowuje dane
    private CityData cityData;

    private WeatherData weatherData;

    private  ForecastData forecastData;
    // forecastData

    public Weather(CityData cityData, WeatherData weatherData, ForecastData forecastData) {
        this.cityData = cityData;
        this.weatherData = weatherData;
        this.forecastData = forecastData;
    }

    public CityData getCityData() {
        return cityData;
    }

    public WeatherData getWeatherData() {
        return weatherData;
    }

    public ForecastData getForecastData() {
        return forecastData;
    }
}
