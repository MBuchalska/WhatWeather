package pl.martabuchalska.model.client;

import pl.martabuchalska.model.Weather;

import java.io.IOException;

public interface WeatherClient {
    Weather getWeather(String cityName) throws IOException;
}
