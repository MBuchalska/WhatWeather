package pl.martabuchalska;

import javafx.application.Application;
import javafx.stage.Stage;
import pl.martabuchalska.view.ViewFactory;


import java.io.*;



public class Launcher extends Application {
    public static void main(String[] args) throws IOException {
        System.out.println("Hello World!");
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        ViewFactory viewFactory = new ViewFactory();
        viewFactory.showMainPage();
    }
}
