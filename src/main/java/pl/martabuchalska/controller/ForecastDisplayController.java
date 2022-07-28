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

    @FXML
    private Label tempForecast;



    public void setForecastData(ForecastData forecastData) {
        this.forecastData = forecastData;
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        //date for each forecast
        Date date = new Date(forecastData.dt*1000);
        Format format = new SimpleDateFormat("dd-MMMM-yyyy HH:mm:ss");
        String formattedDate = format.format(date);
        forecastDate.setText(formattedDate);

        //image
        String imageSource = "http://openweathermap.org/img/wn/"+forecastData.icon+"@2x.png";
        imageWeather.setImage(new Image(imageSource));

        // weather data display
        String description = forecastData.main + ", " + forecastData.description;
        forecastDescription.setText(description);
        double temp = (Math.round((forecastData.temp-272.15)*100))/100;
        tempForecast.setText("temperature: "+String.valueOf(temp) +" C");
        pressureForecast.setText("pressure: "+String.valueOf(forecastData.pressure)+" hPa");
        humidityForecast.setText("humidity: "+String.valueOf(forecastData.humidity)+"%");

    }

}
