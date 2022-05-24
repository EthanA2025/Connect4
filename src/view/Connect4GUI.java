package view;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.Board;
import model.Gamestate;

public class Connect4GUI extends Application {
    private Board board;

    public Button createPlaceButton() {
        Button button = new Button();
        button.setText("");

        return button;
    }

    public Label createPieceButton() {
        Label label = new Label(); 
        label.setPrefSize(100, 100);
        Image circle = new Image("file:src/img/circle.png");
        ImageView view = new ImageView(circle);
        view.setPreserveRatio(true);
        view.setFitWidth(100);
        view.setFitHeight(100);
        
        label.setGraphic(view);

        return label;
    }

    @Override
    public void start(Stage stage) throws Exception {
        board = new Board(Gamestate.IN_PROGRESS);
        GridPane grid = new GridPane();        

        for (int row=0; row<board.getRows(); row++) {
            for (int col=0; col<board.getCols(); col++) {
                Label piece = createPieceButton();
                grid.add(piece, row, col);
            }
        }

        Scene scene = new Scene(grid);
        stage.setScene(scene);     
        stage.setTitle("Connect 4"); 
        stage.show();  
    }

    public static void main(String[] args) {
        launch(args);
    }
}