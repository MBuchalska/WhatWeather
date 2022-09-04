package pl.martabuchalska.model;

import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import pl.martabuchalska.model.client.RealWeatherClient;
import pl.martabuchalska.model.client.WeatherClient;

import java.io.IOException;
import java.nio.file.WatchEvent;
import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class WeatherServiceTest {

    @InjectMocks
    private WeatherService weatherService;

    @Mock
   // private WeatherClient weatherClient = mock(WeatherClient.class);  // Mockito can only mock non-private & non-final classes.
   // private WeatherClient weatherClient = mock(RealWeatherClient.class); //Mockito can only mock non-private & non-final classes.
   // private WeatherClient weatherClient; //Cannot invoke "pl.martabuchalska.model.client.WeatherClient.getWeather(String)" because "this.weatherClient" is null
 /*   private WeatherClient weatherClient = new WeatherClient() {
        @Override
        public Weather getWeather(String cityName) throws IOException {
            return new Weather(new CityData(), new WeatherData(), new ArrayList<ForecastData>());
        }
    };*/  //when() requires an argument which has to be 'a method call on a mock'.
    private WeatherClient weatherClient = new RealWeatherClient(); // when() requires an argument which has to be 'a method call on a mock'.


    private CityData cityData = new CityData("Londyn", 51.5073219, -0.1276474, "GB","England");
    private WeatherData weatherData = new WeatherData("Clouds", "overcast clouds", 296.08, 1015, 62, "04d");
    private ArrayList<ForecastData> forecastData = generateForecastData();

    private ArrayList<ForecastData> generateForecastData() {
        ArrayList<ForecastData> forecastDataArray = new ArrayList<ForecastData>();
        ForecastData forecastData1 = new ForecastData(1662314400,"Clouds", "overcast clouds", 296.08, 1015, 62, "04d");
        for (int i = 0; i<40; i++){
            forecastDataArray.add(forecastData1);
        }
        return forecastDataArray;
    }

    @Test
    public void shouldReturnCompleteWeatherForKnownCity() throws IOException {
        //given
        Weather expectedWeather = new Weather(cityData, weatherData, forecastData);
        when(weatherClient.getWeather("Londyn")).thenReturn(expectedWeather);

        //when
        Weather result = weatherClient.getWeather("Londyn");

        //then
        assertThat(result).isEqualTo(expectedWeather);
    }
}