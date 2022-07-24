package pl.martabuchalska.controller;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import pl.martabuchalska.model.ForecastData;
import pl.martabuchalska.view.ViewFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class ForecastDisplayController extends BaseController implements Initializable{
    public ForecastDisplayController(ViewFactory viewFactory, String fxmlName) {
        super(viewFactory, fxmlName);
    }
    private ForecastData forecastData;

    @FXML
    private Label forecastDate;

    @FXML
    private Label forecastDescription;

    @FXML
    private Label humidityForecast;

    @FXML
    private ImageView imageWeather;

    @FXML
    private Label pressureForecast;

    private String practiceString;

    @FXML
    private Label tempForecast;




    public void setForecastData(ForecastData forecastData) {
        this.forecastData = forecastData;
    }

    public void setPracticeString(String practiceString) {
        this.practiceString = practiceString;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("inicjalizacja");
        tempForecast.setText(practiceString);
        System.out.println(forecastData.pressure);

    }


    public void getTrainingFunciton() {
        System.out.println(forecastData.pressure);
        System.out.println(practiceString);
      //  tempForecast.setText(practiceString);
    }
}
