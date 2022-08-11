package pl.martabuchalska.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import pl.martabuchalska.controller.BaseController;
import pl.martabuchalska.controller.ForecastDisplayController;
import pl.martabuchalska.controller.MainPageController;
import pl.martabuchalska.controller.WeatherDisplayController;
import pl.martabuchalska.model.ForecastData;
import pl.martabuchalska.model.Weather;

import java.io.IOException;

public class ViewFactory {

    public void showMainPage(){
        BaseController controller = new MainPageController(this, "MainPageView.fxml");
        initializeStage(controller);
        }

    public void showWeatherDisplayPage(Weather initialCityWeather, Weather destinationCityWeather){
        BaseController controller = new WeatherDisplayController(this, "WeatherDisplayView.fxml", initialCityWeather, destinationCityWeather);
        initializeStage(controller);
    }

    public Parent showForecastDisplay(ForecastData forecastData) throws IOException {
        ForecastDisplayController controller = new ForecastDisplayController(null, "ForecastDisplayView.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(controller.getFxmlName()));
        controller.setForecastData(forecastData);
        fxmlLoader.setController(controller);

        Parent parent;
        parent = fxmlLoader.load();
        return parent;
    }

    private static void initializeStage(BaseController controller){
        FXMLLoader fxmlLoader = new FXMLLoader(ViewFactory.class.getResource(controller.getFxmlName()));
        fxmlLoader.setController(controller);

        Parent parent;
        try {
            parent = fxmlLoader.load();
        }catch(IOException e){
            e.printStackTrace();
            return;
        }

        Scene scene = new Scene(parent);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

}
