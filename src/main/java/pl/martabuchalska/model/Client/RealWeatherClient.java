package pl.martabuchalska.model.Client;

import com.google.gson.Gson;
import pl.martabuchalska.controller.BaseController;
import pl.martabuchalska.controller.MainPageController;
import pl.martabuchalska.model.CityData;
import pl.martabuchalska.model.Config;
import pl.martabuchalska.model.Weather;
import pl.martabuchalska.view.ViewFactory;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class RealWeatherClient implements WeatherClient {

    // this class gets weather data from the rest of the model
    Weather weather;
    String apiKey = Config.API_KEY;

    @Override
    public Weather getWeather(String cityName) throws IOException {

        String webPage = "http://api.openweathermap.org/geo/1.0/direct?q="+cityName+"&limit=1&appid="+apiKey;
        URL url = new URL(webPage);
        CityData cityData = getCityCoordinates(url, cityName);

        //get weather data having city coords

        return null; // returns weather data to display
    }

    private CityData getCityCoordinates(URL url, String cityName) throws IOException {
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.connect();

        int response = conn.getResponseCode();

        if (response==200){
            String jsonString = getJsonStringFromUrl(url);

            if (jsonString=="") {
                System.out.println("City "+cityName+" was not found in the database");
                return null;
            }
            else {
                Gson gson = new Gson();
                CityData cityData = gson.fromJson(jsonString, CityData.class);

                return cityData;
            }
        }
        else {
            System.out.println("API server is not responding. Try again.");
            return null;
        }
    }

    private String getJsonStringFromUrl(URL url) throws IOException {
        String inline = "";
        Scanner scanner = new Scanner(url.openStream());

        while (scanner.hasNext()) {
            inline += scanner.nextLine();
        }
        scanner.close();

        String jsonString = inline.substring(1, inline.length() - 1);
        return jsonString;
    }


}
