package pl.martabuchalska.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import pl.martabuchalska.view.ViewFactory;

public class MainPageController extends BaseController {

    @FXML
    private TextField destinationCityText;

    @FXML
    private Label errorLabel;

    @FXML
    private TextField initialCityText;

    public MainPageController(ViewFactory viewFactory, String fxmlName) {
        super(viewFactory, fxmlName);
    }

    @FXML
    void getCityButton() {
// sprawdzić czy uzupełniono napisy; pobrać co jest napisane, przepuścić przez Api, wyświetlić wyniki w nowym widoku jako listy radio
        System.out.println("Klik");
    }
}
