package pl.martabuchalska.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import pl.martabuchalska.model.Weather;
import pl.martabuchalska.model.WeatherService;
import pl.martabuchalska.view.ViewFactory;

public class MainPageController extends BaseController {

    @FXML
    private TextField initialCityText;
    @FXML
    private TextField destinationCityText;

    @FXML
    private Label errorLabel;

    private WeatherService weatherService;

    public MainPageController(ViewFactory viewFactory, String fxmlName) {
        super(viewFactory, fxmlName);
    }

    @FXML
    void getCityButton() {
// sprawdzić czy uzupełniono napisy; pobrać co jest napisane, przepuścić przez Api, wyświetlić wyniki w nowym widoku jako listy radio
        System.out.println(initialCityText.getText());
        System.out.println(destinationCityText.getText());
        System.out.println("Klik");

        if((initialCityText.getText().isEmpty()) && (destinationCityText.getText().isEmpty())){
            errorLabel.setText("Both city names are missing");
        } else if (initialCityText.getText().isEmpty()) {
            errorLabel.setText("Initial city name is missing");
        } else if (destinationCityText.getText().isEmpty()) {
            errorLabel.setText("Destination city name is missing");
        }
        else{
            System.out.println("Pienknie");
            String initialCityName = initialCityText.getText();
            String destinationCityName = destinationCityText.getText();

            Weather initialCityWeather = weatherService.getWeather(initialCityName);
           // Weather destinationCityWeather = weatherService.getWeather(destinationCityName);

            // display weather displayWeather(weather, weather2);
        }
    }

//    private void displayWeather(weather, weather2){
//        // wywołać widok view factory już całościowy
//    }
}
