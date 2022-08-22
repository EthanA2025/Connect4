package view;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
    private int placeButtonNum;
    private Label statLabel = createStatusLabel();

    public Label createStatusLabel() {
        Label status = new Label();
        status.setText("RED's turn!");
        status.setBackground(new Background(new BackgroundFill(Color.SALMON, CornerRadii.EMPTY, Insets.EMPTY)));
        status.setPrefWidth(600);
        status.setAlignment(Pos.CENTER);
        status.setFont(new Font("Arial", 24));

        return status;
    }

    public void updateStatus() {
        if (board.getTurnsPlayed()%2 == 1) {
            statLabel.setBackground(new Background(new BackgroundFill(Color.YELLOW, CornerRadii.EMPTY, Insets.EMPTY)));
            statLabel.setText("YELLOWS's turn!");
        } else {
            statLabel.setText("RED's turn!");
            statLabel.setBackground(new Background(new BackgroundFill(Color.SALMON, CornerRadii.EMPTY, Insets.EMPTY)));
        }
    }

    public void GUIwin() {
        statLabel.setBackground(new Background(new BackgroundFill(Color.AQUAMARINE, CornerRadii.EMPTY, Insets.EMPTY)));
        statLabel.setText(board.gameWon());
    }

    public Button createPlaceButton() {
        Button button = new Button();
        button.setPrefSize(100, 100);
        button.setText("");
        int column = placeButtonNum;
        placeButtonNum++;

        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (!board.getGamestate().equals(Gamestate.WON)) {
                    board.placePiece(column);
                    updateStatus();
                    board.checkConnections();
                    if (board.getGamestate().equals(Gamestate.WON)) {
                        GUIwin();
                    }
                }
            }
        });

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
        bp.setBottom(this.statLabel);

        Scene scene = new Scene(bp);
        stage.setScene(scene);     
        stage.setTitle("Connect 4"); 
        stage.show();  
    }

    public static void main(String[] args) {
        launch(args);
    }
}