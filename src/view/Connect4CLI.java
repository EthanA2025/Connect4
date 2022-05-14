package view;

import java.util.Scanner;

import model.Board;
import model.Gamestate;
import model.Piece;

public class Connect4CLI {
    private final static Board board = new Board(Gamestate.IN_PROGRESS);

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        System.out.println("Welcome to connect 4!");
        System.out.println("Enter commands below! Use 'help' to get a list of commands.");
        while (!board.getGamestate().equals(Gamestate.WON) && !board.getGamestate().equals(Gamestate.QUIT)) {
            System.out.print("> ");
            String command = s.next().toLowerCase();

            switch(command) {
                case "help":
                    System.out.println("Commands:\n"
                        + "\thelp - gives a list of all commands\n"
                        + "\tplace #column - given a column place a token, players take alternating turns\n"
                        + "\tquit - exits the game"
                    );
                    break;

                case "quit":
                    board.setGamestate(Gamestate.QUIT);
                    break;

                case "place":
                    int col = Integer.valueOf(s.next());
                    System.out.println("Placing piece at column: " + col);
                    board.placePiece(col);

                default:
                    System.out.println("unkown command");
                    break;
            }
        }   
        s.close();
        System.out.println("Goodbye!");
    }
}
