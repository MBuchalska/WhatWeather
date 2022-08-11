package pl.martabuchalska;

import javafx.application.Application;
import javafx.stage.Stage;
import pl.martabuchalska.view.ViewFactory;


import java.io.*;



public class Launcher extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        ViewFactory viewFactory = new ViewFactory();
        viewFactory.showMainPage();
    }
}
