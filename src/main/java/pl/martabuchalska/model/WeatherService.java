package pl.martabuchalska.model;

import pl.martabuchalska.model.client.WeatherClient;

import java.io.IOException;

public class WeatherService {

    private final WeatherClient weatherClient;

    public WeatherService(WeatherClient weatherClient){
        this.weatherClient = weatherClient;
    }

    public Weather getWeather(String cityName) throws IOException {

            return weatherClient.getWeather(cityName);

    }
}
