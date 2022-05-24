package view;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.Board;
import model.Gamestate;

public class Connect4GUI extends Application {
    private final static Board board = new Board(Gamestate.IN_PROGRESS);
    static final Image CIRCLE = new Image("file:src/view/img/circle.jpeg");

    public Button createPlaceButton() {
        Button button = new Button();
        button.setText("");

        return button;
    }

    public Label createPieceLabel() {
        Label label = new Label();
        label.setBackground(new Background(new BackgroundImage(CIRCLE, 
        BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, 
        BackgroundSize.DEFAULT)));

        return label;
    }

    @Override
    public void start(Stage stage) throws Exception {
        GridPane grid = new GridPane();

        for (int row=0; row<board.getRows(); row++) {
            for (int col=0; col<board.getCols(); col++) {
                grid.add(createPieceLabel(), row, col);
            }
        }
        
        BorderPane bp = new BorderPane();
        bp.setCenter(grid);

        Scene scene = new Scene(bp);
        stage.setTitle("Connect 4"); 
        stage.setScene(scene);     
        stage.show();  
    }

    public static void main(String[] args) {
        launch(args);
    }
}