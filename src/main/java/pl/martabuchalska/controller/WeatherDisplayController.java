package pl.martabuchalska.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import pl.martabuchalska.model.Weather;
import pl.martabuchalska.view.ViewFactory;

import java.net.URL;
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

    // labels only

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


// with list view
    @FXML
    private ImageView imageInitialToday1;

    @FXML
    private ListView<String> listTableTest;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Settings settings = new Settings();
        Weather initialCityWeather = settings.getInitialCityWeather();
        Weather destinationCityWeather = settings.getDestinationCityWeather();

        System.out.println("11111");
        System.out.println(initialCityNameExtended.getText());
        System.out.println(initialCityWeather.getCityData().name);
        System.out.println(destinationCityWeather.getWeatherData().temp);
    }


}
