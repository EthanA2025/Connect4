package view;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import model.Board;
import model.Gamestate;

public class Connect4GUI extends Application {
    private Board board;

    public Label createStatusLabel() {
        Label status = new Label();
        status.setText("RED's turn!");
        status.setBackground(new Background(new BackgroundFill(Color.SALMON, CornerRadii.EMPTY, Insets.EMPTY)));
        status.setPrefWidth(600);
        status.setAlignment(Pos.CENTER);
        status.setFont(new Font("Arial", 24));

        return status;
    }

    public Button createPlaceButton() {
        Button button = new Button();
        button.setPrefSize(100, 100);
        button.setText("");

        return button;
    }

    public Label createPieceLabel() {
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
        BorderPane bp = new BorderPane();
        HBox hbox = new HBox();
        Label status = createStatusLabel();

        for (int row=0; row<board.getRows(); row++) {
            for (int col=0; col<board.getCols(); col++) {
                Label piece = createPieceLabel();
                grid.add(piece, row, col);
            }
        }

        for (int i=1; i<board.getCols(); i++) {
            hbox.getChildren().add(createPlaceButton());
        }

        bp.setCenter(grid);
        bp.setTop(hbox);
        bp.setBottom(status);

        Scene scene = new Scene(bp);
        stage.setScene(scene);     
        stage.setTitle("Connect 4"); 
        stage.show();  
    }

    public static void main(String[] args) {
        launch(args);
    }
}