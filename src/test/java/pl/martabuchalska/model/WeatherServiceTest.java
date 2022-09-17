package pl.martabuchalska.model;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import pl.martabuchalska.model.client.RealWeatherClient;
import pl.martabuchalska.model.client.WeatherClient;

import java.io.IOException;
import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class WeatherServiceTest {

    @InjectMocks
    private WeatherService weatherService;

    @Mock
    private WeatherClient weatherClient = mock(WeatherClient.class);


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

    Weather expectedWeather = new Weather(cityData, weatherData, forecastData);
    WeatherService weatherServiceBase = new WeatherService(weatherClient);

    @Test
    public void shouldReturnCorrectWeatherFromWeatherServiceAndKnownCity() throws IOException {
        //given
        when(weatherServiceBase.getWeather("Londyn")).thenReturn(expectedWeather);

        //when
        Weather result = weatherServiceBase.getWeather("Londyn");

        //then
        assertThat(result).isEqualTo(expectedWeather);
    }

    @Test
    public void shouldNotReturnWeatherForWeatherServiceAndUnknownCity() throws IOException {
        //given
        when(weatherServiceBase.getWeather("Londyn")).thenReturn(expectedWeather);

        //when
        Weather result = weatherServiceBase.getWeather("Rzym");

        //then
        assertThat(result).isNotEqualTo(expectedWeather);
    }

}