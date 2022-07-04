module WhatWeather {

    exports pl.martabuchalska;

    requires javafx.fxml;
    requires javafx.controls;

    opens pl.martabuchalska.controller to javafx.fxml, javafx.controls;
    opens pl.martabuchalska.view to javafx.fxml, javafx.controls;
    opens pl.martabuchalska.model to javafx.fxml, javafx.controls;

}