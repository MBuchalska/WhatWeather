package pl.martabuchalska.controller;


import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import pl.martabuchalska.model.ForecastData;
import pl.martabuchalska.model.Weather;
import pl.martabuchalska.view.ViewFactory;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class WeatherDisplayController extends BaseController implements Initializable {

// city data
    @FXML
    private Label dateToday;

    @FXML
    private Label destinationCityNameExtended;

    @FXML
    private Label initialCityNameExtended;

    // weather labels

    @FXML
    private Label descriptionDestinationToday;

    @FXML
    private Label descriptionInitialToday;

    @FXML
    private Label humidityDestinationToday;

    @FXML
    private Label humidityInitialToday;

    @FXML
    private ImageView imageDestinationToday;

    @FXML
    private ImageView imageInitialToday;

    @FXML
    private Label pressureDestinationToday;

    @FXML
    private Label pressureInitialToday;

    @FXML
    private Label tempDestinationToday;

    @FXML
    private Label tempInitialToday;

    // VBox for forecast

    @FXML
    private VBox initialForecastBox;

    @FXML
    private VBox destinationForecastBox;

    // Weather Data from model
    private final Weather initialCityWeather;
    private final Weather destinationCityWeather;

    public WeatherDisplayController(ViewFactory viewFactory, String fxmlName, Weather initialCityWeather, Weather destinationCityWeather) {
        super(viewFactory, fxmlName);
        this.initialCityWeather = initialCityWeather;
        this.destinationCityWeather = destinationCityWeather;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        setLocalDate();
        setCityNames(initialCityWeather, destinationCityWeather);
        setWeatherIconImages(initialCityWeather, destinationCityWeather);
        setWeatherNumericalData();

        // forecast display in VBox with separate view

        for(int i=Settings.FIRST_FORECAST_AFTER_ONE_DAY; i<Settings.TOTAL_NUMBER_OF_FORECAST_DATA; i++)
        {
            ForecastData initialForecastData = initialCityWeather.getForecastData().get(i);
            ForecastData destinationForecastData = destinationCityWeather.getForecastData().get(i);
            try {
                populateInitialForecastBox(initialForecastData);
                populateDestinationForecastBox(destinationForecastData);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            i+=Settings.FORECAST_DATA_PER_DAY;
        }
    }

    private void setWeatherNumericalData() {
        String initialDescription = this.initialCityWeather.getWeatherData().main+", " + this.initialCityWeather.getWeatherData().description;
        descriptionInitialToday.setText(initialDescription);
        double temp1 = (Math.round((this.initialCityWeather.getWeatherData().temp-272.15)*100))/100.0;
        tempInitialToday.setText("temperature: "+ temp1 +" C");
        pressureInitialToday.setText("pressure: "+ this.initialCityWeather.getWeatherData().pressure +" hPa");
        humidityInitialToday.setText("humidity: "+ this.initialCityWeather.getWeatherData().humidity +"%");


        String destinationDescription = this.destinationCityWeather.getWeatherData().main+", "+ this.destinationCityWeather.getWeatherData().description;
        descriptionDestinationToday.setText(destinationDescription);
        double temp2 = (Math.round((this.destinationCityWeather.getWeatherData().temp-272.15)*100))/100.0;
        tempDestinationToday.setText("temperature: "+ temp2 +" C");
        pressureDestinationToday.setText("pressure: "+ this.destinationCityWeather.getWeatherData().pressure +" hPa");
        humidityDestinationToday.setText("humidity: "+ this.destinationCityWeather.getWeatherData().humidity +"%");
    }

    private void setWeatherIconImages(Weather initialCityWeather, Weather destinationCityWeather) {
        String imageSource = "http://openweathermap.org/img/wn/"+ initialCityWeather.getWeatherData().icon+"@2x.png";
        imageInitialToday.setImage(new Image(imageSource));

        String imageSource1 = "http://openweathermap.org/img/wn/"+ destinationCityWeather.getWeatherData().icon+"@2x.png";
        imageDestinationToday.setImage(new Image(imageSource1));
    }

    private void setCityNames(Weather initialCityWeather, Weather destinationCityWeather) {
        String initialCity = initialCityWeather.getCityData().name + ", "+ initialCityWeather.getCityData().country;
        String destinationCity = destinationCityWeather.getCityData().name+ ", "+ destinationCityWeather.getCityData().country;
        initialCityNameExtended.setText(initialCity);
        destinationCityNameExtended.setText(destinationCity);
    }

    private void setLocalDate() {
        LocalDate dateForToday = LocalDate.now();

        String formattedDate = dateForToday.format(Settings.DATE_FORMAT);
        dateToday.setText(formattedDate);
    }

    private void populateDestinationForecastBox(ForecastData forecastData) throws IOException {

        ViewFactory viewFactory = new ViewFactory();

        Parent parent1 = viewFactory.showForecastDisplay(forecastData);
        destinationForecastBox.getChildren().add(parent1);
    }

    private void populateInitialForecastBox(ForecastData forecastData) throws IOException {

        ViewFactory viewFactory = new ViewFactory();

        Parent parent = viewFactory.showForecastDisplay(forecastData);
        initialForecastBox.getChildren().add(parent);
    }

}
