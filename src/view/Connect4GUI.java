package view;

import javafx.application.Application;
import javafx.stage.Stage;

public class Connect4GUI extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Connect 4");      
        stage.show();  
    }

    public static void main(String[] args) {
        launch(args);
    }
    
}
