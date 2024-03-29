package pl.martabuchalska.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import pl.martabuchalska.model.client.RealWeatherClient;
import pl.martabuchalska.model.Weather;
import pl.martabuchalska.model.WeatherService;
import pl.martabuchalska.view.ViewFactory;

import java.io.IOException;
import java.net.MalformedURLException;

public class MainPageController extends BaseController {

    @FXML
    private TextField initialCityText;
    @FXML
    private TextField destinationCityText;

    @FXML
    private Label errorLabel;

    private final WeatherService weatherService = new WeatherService(new RealWeatherClient());

    public MainPageController(ViewFactory viewFactory, String fxmlName) {
        super(viewFactory, fxmlName);
    }

    @FXML
    void getCityButton() throws IOException {

        if((initialCityText.getText().isEmpty()) && (destinationCityText.getText().isEmpty())){
            errorLabel.setText("Both city names are missing");
        } else if (initialCityText.getText().isEmpty()) {
            errorLabel.setText("Initial city name is missing");
        } else if (destinationCityText.getText().isEmpty()) {
            errorLabel.setText("Destination city name is missing");
        }
        else{
            getWeatherForCities();
        }
    }

    private void getWeatherForCities() throws IOException {
        String initialCityName = initialCityText.getText();
        String destinationCityName = destinationCityText.getText();

        Weather initialCityWeather = weatherService.getWeather(initialCityName);
        Weather destinationCityWeather = weatherService.getWeather(destinationCityName);


        if ((initialCityWeather.getCityData().name == null)&&(destinationCityWeather.getCityData().name == null)){
            errorLabel.setText("Cities are not in the database");
        } else if (initialCityWeather.getCityData().name == null) {
            errorLabel.setText("Initial city is not in the database");
        } else if (destinationCityWeather.getCityData().name == null) {
            errorLabel.setText("Destination city is not in the database");
        }
        else {
            displayWeather(initialCityWeather, destinationCityWeather);
        }
    }

    private void displayWeather(Weather initialCityWeather, Weather destinationCityWeather){
        ViewFactory viewFactory = new ViewFactory();
        viewFactory.showWeatherDisplayPage(initialCityWeather, destinationCityWeather);
    }

}
