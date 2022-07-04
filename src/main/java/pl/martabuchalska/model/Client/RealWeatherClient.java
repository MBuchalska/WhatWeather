package pl.martabuchalska.model.Client;

import pl.martabuchalska.model.Weather;

public class RealWeatherClient implements WeatherClient {

    // this class gets weather data from the rest of the model
    @Override
    public Weather getWeather(String cityName) {
        return null; // zwróci dane które trzeba przekazać do widoku chyba
    }
}
