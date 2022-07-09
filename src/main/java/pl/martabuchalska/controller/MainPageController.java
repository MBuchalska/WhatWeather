package pl.martabuchalska.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import pl.martabuchalska.model.Client.RealWeatherClient;
import pl.martabuchalska.model.Client.WeatherClient;
import pl.martabuchalska.model.Weather;
import pl.martabuchalska.model.WeatherService;
import pl.martabuchalska.view.ViewFactory;

import java.io.IOException;

public class MainPageController extends BaseController {

    @FXML
    private TextField initialCityText;
    @FXML
    private TextField destinationCityText;

    @FXML
    private Label errorLabel;

    private WeatherService weatherService = new WeatherService(new RealWeatherClient());

    public MainPageController(ViewFactory viewFactory, String fxmlName) {
        super(viewFactory, fxmlName);
    }

    @FXML
    void getCityButton() throws IOException {

        System.out.println("Processing your cities");

        if((initialCityText.getText().isEmpty()) && (destinationCityText.getText().isEmpty())){
            errorLabel.setText("Both city names are missing");
        } else if (initialCityText.getText().isEmpty()) {
            errorLabel.setText("Initial city name is missing");
        } else if (destinationCityText.getText().isEmpty()) {
            errorLabel.setText("Destination city name is missing");
        }
        else{
            System.out.println("Checking weather for your cities");
            String initialCityName = initialCityText.getText();
            String destinationCityName = destinationCityText.getText();

            Weather initialCityWeather = weatherService.getWeather(initialCityName);
           // Weather destinationCityWeather = weatherService.getWeather(destinationCityName);

            // display weather displayWeather(initialCityWeather, destinationCityWeather);
        }
    }

//    private void displayWeather(weather, weather2){
//        // wywołać widok view factory już całościowy
//    }
}
