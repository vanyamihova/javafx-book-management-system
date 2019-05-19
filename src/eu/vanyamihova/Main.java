package eu.vanyamihova;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage defaultStageIgnored) {
        eu.vanyamihova.launch.Stage stage = new eu.vanyamihova.launch.Stage();
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
