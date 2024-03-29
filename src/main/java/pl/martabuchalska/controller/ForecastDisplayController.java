package pl.martabuchalska.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import pl.martabuchalska.model.ForecastData;
import pl.martabuchalska.view.ViewFactory;

import java.net.URL;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

public class ForecastDisplayController extends BaseController implements Initializable{

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

    @FXML
    private Label tempForecast;

    public ForecastDisplayController(ViewFactory viewFactory, String fxmlName) {
        super(viewFactory, fxmlName);
    }

    public void setForecastData(ForecastData forecastData) {
        this.forecastData = forecastData;
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        setForecastDate();
        setWeatherIcon();
        setWeatherDescription();
        setNumericalWeatherData();
    }

    private void setNumericalWeatherData() {
        double temp = (Math.round((forecastData.temp-272.15)*100))/100.0;
        tempForecast.setText("temperature: "+temp +" C");
        pressureForecast.setText("pressure: "+ forecastData.pressure +" hPa");
        humidityForecast.setText("humidity: "+ forecastData.humidity +"%");
    }

    private void setWeatherDescription() {
        String description = forecastData.main + ", " + forecastData.description;
        forecastDescription.setText(description);
    }

    private void setWeatherIcon() {
        String imageSource = "http://openweathermap.org/img/wn/"+forecastData.icon+"@2x.png";
        imageWeather.setImage(new Image(imageSource));
    }

    private void setForecastDate() {
        Date date = new Date(forecastData.dt*1000);
        Format format = new SimpleDateFormat("dd-MMMM-yyyy HH:mm:ss");
        String formattedDate = format.format(date);
        forecastDate.setText(formattedDate);
    }

}
