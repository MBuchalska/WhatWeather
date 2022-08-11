package pl.martabuchalska.model;

import pl.martabuchalska.model.client.RealWeatherClient;
import pl.martabuchalska.model.client.WeatherClient;

public class WeatherServiceFactory {  //allows getting object instance without the knowledge of class details

    public static WeatherService createWeatherService(){
        return new WeatherService(createWeatherClient());
    }

    private static WeatherClient createWeatherClient(){
        return new RealWeatherClient();
    }
}
