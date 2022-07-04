package pl.martabuchalska.model.Client;

import pl.martabuchalska.model.Weather;

public interface WeatherClient {
    Weather getWeather(String cityName);
    // potem tu też metodę get forcast?
}
