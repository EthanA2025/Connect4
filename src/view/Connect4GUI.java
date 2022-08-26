package view;

import java.util.ArrayList;
import java.util.HashMap;

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

/**
 * GUI for the Connect 4 game
 * @author Ethan Abbate
 */

public class Connect4GUI extends Application {
    private Board board;
    private int placeButtonNum = 0;
    private Label statLabel = createStatusLabel();
    private final GridPane grid = new GridPane();        
    private HashMap<ArrayList<Integer>, Label> pieces = new HashMap<>();

    /**
     * Method to create the status label of the game
     * this label indicates to the users the current
     * status of the game (in progress, completed)
     * @return status label
     */
    public Label createStatusLabel() {
        Label status = new Label();
        status.setText("RED's turn!");
        status.setBackground(new Background(new BackgroundFill(Color.SALMON, CornerRadii.EMPTY, Insets.EMPTY)));
        status.setPrefWidth(700);
        status.setAlignment(Pos.CENTER);
        status.setFont(new Font("Arial", 24));

        return status;
    }
    
    /**
     * A method for updating the graphic of the piece in the connect 4 board
     * @param row the row of the piece being updated
     * @param col the column of the piece being updated
     */
    public void updatePiece(int row, int col) {
        ArrayList<Integer> rowCol = new ArrayList<>();
        rowCol.add(5 - row); // subtract 5 from the row so that the pieces go in botton -> top or else the pieces will appear to be placed at the top
        rowCol.add(col);     // of the board.

        Label toUpdate = pieces.get(rowCol);
        if (board.getTurnsPlayed()%2 == 0) {
            Image circle = new Image("file:src/img/yellow_token.png");
            ImageView view = new ImageView(circle);
            view.setPreserveRatio(true);
            view.setFitWidth(100);
            view.setFitHeight(100);
            toUpdate.setGraphic(view);
        } else {
            Image circle = new Image("file:src/img/red_token.png");
            ImageView view = new ImageView(circle);  
            view.setPreserveRatio(true);
            view.setFitWidth(100);
            view.setFitHeight(100);

            toUpdate.setGraphic(view);
        }
    }

    /**
     * Method to update the status of the label
     * and shows what turn it is (RED or YELLOW)
     */
    public void updateStatus() {
        if (board.getTurnsPlayed()%2 == 1) {
            statLabel.setBackground(new Background(new BackgroundFill(Color.YELLOW, CornerRadii.EMPTY, Insets.EMPTY)));
            statLabel.setText("YELLOWS's turn!");
        } else {
            statLabel.setText("RED's turn!");
            statLabel.setBackground(new Background(new BackgroundFill(Color.SALMON, CornerRadii.EMPTY, Insets.EMPTY)));
        }
    }

    /**
     * Method that is called when the game is won
     * displays which color won (RED or YELLOw)
     */
    public void GUIwin() {
        statLabel.setBackground(new Background(new BackgroundFill(Color.AQUAMARINE, CornerRadii.EMPTY, Insets.EMPTY)));
        statLabel.setText(board.gameWon());
    }   

    /**
     * Creates buttons for the user to place down a piece
     * @return
     */
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
                    int row = board.placePiece(column);
                    updatePiece(row, column);
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

    /**
     * Factory method for creating the labels for the pieces
     * @return
     */
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

    /**
     * Creates the grid of pieces that will be displayed,
     * this represents the Connect 4 board.
     */
    public void createPieces() {
        for (int row=0; row<board.getRows(); row++) {
            for (int col=0; col<board.getCols(); col++) {
                Label piece = createPieceLabel();
                grid.add(piece, col, row);

                ArrayList<Integer> rowCol = new ArrayList<>(); // store row and column in dictionary
                rowCol.add(row);
                rowCol.add(col);

                pieces.put(rowCol, piece);
            }
        }
    }

    @Override
    public void start(Stage stage) throws Exception {
        board = new Board(Gamestate.IN_PROGRESS);
        BorderPane bp = new BorderPane();
        HBox hbox = new HBox();

        createPieces();

        for (int i=0; i<board.getCols(); i++) {
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