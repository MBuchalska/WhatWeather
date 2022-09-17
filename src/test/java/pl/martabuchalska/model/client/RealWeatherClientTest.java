package pl.martabuchalska.model.client;

import org.junit.jupiter.api.Test;
import org.mockito.Spy;
import pl.martabuchalska.model.CityData;
import pl.martabuchalska.model.ForecastData;
import pl.martabuchalska.model.Weather;
import pl.martabuchalska.model.WeatherData;

import java.io.IOException;
import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

public class RealWeatherClientTest {

    @Spy
    private WeatherClient weatherClient=spy(WeatherClient.class);

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

    @Test
    public void shouldReturnCompleteWeatherForKnownCity() throws IOException {
        //given
        when(weatherClient.getWeather("Londyn")).thenReturn(expectedWeather);

        //when
        Weather result = weatherClient.getWeather("Londyn");

        //then
        assertThat(result).isEqualTo(expectedWeather);
    }

    @Test
    public void shouldNotReturnWeatherForUnknownCity() throws IOException {
        //given
        when(weatherClient.getWeather("Londyn")).thenReturn(expectedWeather);

        //when
        Weather result = weatherClient.getWeather("Rzym");

        //then
        assertThat(result).isNotEqualTo(expectedWeather);
    }

    @Test
    public void shouldNotReturnCorrectWeatherForOtherClient() throws IOException {
        //given
        WeatherClient weatherClient1 = new RealWeatherClient();
        when(weatherClient.getWeather("Londyn")).thenReturn(expectedWeather);

        //when
        Weather result = weatherClient1.getWeather("Londyn");

        //then
        assertThat(result).isNotEqualTo(expectedWeather);
    }
}