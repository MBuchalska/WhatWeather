package pl.martabuchalska.model.Client;

import pl.martabuchalska.model.Weather;

import java.io.IOException;
import java.net.MalformedURLException;

public interface WeatherClient {
    Weather getWeather(String cityName) throws IOException;
}
