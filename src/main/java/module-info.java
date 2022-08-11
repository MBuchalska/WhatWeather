module WhatWeather {

    exports pl.martabuchalska;

    requires javafx.fxml;
    requires javafx.controls;
    requires com.google.gson;

    opens pl.martabuchalska.controller to javafx.fxml, javafx.controls, com.google.gson;
    opens pl.martabuchalska.view to javafx.fxml, javafx.controls, com.google.gson;
    opens pl.martabuchalska.model to javafx.fxml, javafx.controls, com.google.gson;
    opens pl.martabuchalska.model.client to com.google.gson;

}