package pl.martabuchalska.model.Client;

import com.google.gson.Gson;
import pl.martabuchalska.model.*;

import java.io.IOException;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class RealWeatherClient implements WeatherClient {

    // this class gets weather data from the rest of the model
    Weather weather;
    String apiKey = Config.API_KEY;

    @Override
    public Weather getWeather(String cityName) throws IOException {

        // get city data
        String webPageForCity = "http://api.openweathermap.org/geo/1.0/direct?q="+cityName+"&limit=1&appid="+Config.API_KEY;
        URL url = new URL(webPageForCity);

        CityData cityData = getCityCoordinates(url, cityName);
        double cityLat = cityData.lat;
        double cityLon = cityData.lon;

        //get weather data having city coordinates
        String webPageForWeather = "https://api.openweathermap.org/data/2.5/weather?lat="+cityLat+"&lon="+cityLon+"&appid="+Config.API_KEY;
        URL url1 = new URL(webPageForWeather);
        WeatherData weatherData = getWeatherData(url1);

        //get weather forecast
        String webPageForForecast = "https://api.openweathermap.org/data/2.5/forecast?lat="+cityLat+"&lon="+cityLon+"&appid="+Config.API_KEY;
        URL url2 = new URL(webPageForForecast);
        ArrayList<ForecastData> forecastData = getForecastData(url2);

        System.out.println(forecastData.get(1).temp);

        ForecastData forecastData1 = new ForecastData(); // to do wywalenia


        // tworzymy nowy obiekt Weather z danymi które tu znaleźliśmy i zwracamy do
        // main page controller które przekazuje do wyświetlania innemu kontrolerowi

        Weather weather = new Weather(cityData, weatherData, forecastData1); // zmienić w klasie pogodowej na ArrayList

        return null; // returns weather to display
    }

    private ArrayList<ForecastData> getForecastData(URL url) throws IOException {
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.connect();

        int response = conn.getResponseCode();

        if (response==200){
            String inline = "";
            Scanner scanner = new Scanner(url.openStream());

            while (scanner.hasNext()) {
                inline += scanner.nextLine();
            }
            scanner.close();
            
            if (inline.isEmpty()) {
                return null;
            }
            else {
                ArrayList<ForecastData> forecastDataList= new ArrayList<ForecastData>();
                Gson gson = new Gson();
                AdditionalData additionalData = gson.fromJson(inline, AdditionalData.class);
                
                for(int i=0; i<5; i++){
                    String listItem = gson.toJson(additionalData.list.get(i));

                    AdditionalData additionalData1 = gson.fromJson(listItem, AdditionalData.class);
                    String dt = gson.toJson(additionalData1.dt);
                    String weather = gson.toJson(additionalData1.weather);
                    String mainData = gson.toJson(additionalData1.main);

                    String finalString = "{\"dt\":" + dt + "," + mainData.substring(1, mainData.length() - 1) + "," + weather.substring(2, weather.length() - 1);

                    ForecastData forecastData = gson.fromJson(finalString, ForecastData.class);

                    forecastDataList.add(forecastData);
                }
//
                return forecastDataList; //forecast data array chyba że się nie da
            }
        }
        else {
            System.out.println("API server is not responding. Try again.");
            return null;
        }
    }

    private WeatherData getWeatherData(URL url) throws IOException {
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.connect();

        int response = conn.getResponseCode();

        if (response==200){
            String inline = "";
            Scanner scanner = new Scanner(url.openStream());

            while (scanner.hasNext()) {
                inline += scanner.nextLine();
            }
            scanner.close();

            if (inline.isEmpty()) {
                return null;
            }
            else {
                Gson gson = new Gson();
                AdditionalData additionalData = gson.fromJson(inline, AdditionalData.class);

                String mainData = gson.toJson(additionalData.main);
                mainData=mainData.substring(0,mainData.length()-1);

                String mainData2 = gson.toJson(additionalData.weather);
                mainData2 = mainData2.substring(2, mainData2.length()-1);

                mainData+=", "+ mainData2;

                WeatherData weatherData = gson.fromJson(mainData, WeatherData.class);
                return weatherData;
            }
        }
        else {
            System.out.println("API server is not responding. Try again.");
            return null;
        }
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
class AdditionalData{
    public Object main;
    public Object weather;
    public ArrayList<Object> list;
    public Integer dt;
}

