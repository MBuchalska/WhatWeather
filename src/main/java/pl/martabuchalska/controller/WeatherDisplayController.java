package pl.martabuchalska.controller;


import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
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
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class WeatherDisplayController extends BaseController implements Initializable {
    public WeatherDisplayController(ViewFactory viewFactory, String fxmlName) {
        super(viewFactory, fxmlName);
    }

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
    private Group groupDestinationToday;

    @FXML
    private Group groupInitialToday;

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


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Settings settings = new Settings();
        Weather initialCityWeather = settings.getInitialCityWeather();
        Weather destinationCityWeather = settings.getDestinationCityWeather();

        setLocalDate();
        setCityNames(initialCityWeather, destinationCityWeather);
        setWeatherIconImages(initialCityWeather, destinationCityWeather);
        setWeatherNumericalData(initialCityWeather, destinationCityWeather);

        // forecast display in VBox with separate view
        for(int i=4; i<40; i++) // forecast for 2 p.m.
        {
            ForecastData forecastData = initialCityWeather.getForecastData().get(i);
            ForecastData forecastData1 = destinationCityWeather.getForecastData().get(i);
            try {
                populateInitialForecastBox(forecastData);
                populateDestinationForecastBox(forecastData1);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            i+=7;
        }
    }

    private void setWeatherNumericalData(Weather initialCityWeather, Weather destinationCityWeather) {
        String initialDescription = initialCityWeather.getWeatherData().main+", " + initialCityWeather.getWeatherData().description;
        descriptionInitialToday.setText(initialDescription);
        double temp1 = (Math.round((initialCityWeather.getWeatherData().temp-272.15)*100))/100;
        tempInitialToday.setText("temperature: "+String.valueOf(temp1) +" C");
        pressureInitialToday.setText("pressure: "+String.valueOf(initialCityWeather.getWeatherData().pressure)+" hPa");
        humidityInitialToday.setText("humidity: "+String.valueOf(initialCityWeather.getWeatherData().humidity)+"%");


        String destinationDescription = destinationCityWeather.getWeatherData().main+", "+ destinationCityWeather.getWeatherData().description;
        descriptionDestinationToday.setText(destinationDescription);
        double temp2 = (Math.round((destinationCityWeather.getWeatherData().temp-272.15)*100))/100;
        tempDestinationToday.setText("temperature: "+String.valueOf(temp2)+" C");
        pressureDestinationToday.setText("pressure: "+String.valueOf(destinationCityWeather.getWeatherData().pressure)+" hPa");
        humidityDestinationToday.setText("humidity: "+String.valueOf(destinationCityWeather.getWeatherData().humidity)+"%");
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
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MMMM-yyyy");
        String formattedDate = dateForToday.format(myFormatObj);
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
