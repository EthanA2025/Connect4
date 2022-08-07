package view;

import java.util.Scanner;

import model.Board;
import model.Gamestate;

public class Connect4CLI {
    final Board board = new Board(Gamestate.IN_PROGRESS);

    public void start() {
        Scanner s = new Scanner(System.in);

        System.out.println("Welcome to connect 4!");
        System.out.println("Enter commands below! Use 'help' to get a list of commands.");
        while (!board.getGamestate().equals(Gamestate.WON) && !board.getGamestate().equals(Gamestate.QUIT)) {
            System.out.println(board.toString());
            System.out.print("> ");
            String command = s.next().toLowerCase();

            switch(command) {
                case "help":
                    System.out.println("Goal of the game: Each player must connect 4 pieces diagonally, vertically, or horizontally to win.\n"
                        + "Players take alternating turns between RED and YELLOW pieces. RED goes first in the game.\n"
                        + "Players place pieces between a column 0-6 to choose from which places the piece.\n"
                        + "Commands:\n"
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
                    board.checkConnections();
                    break;

                default:
                    System.out.println("unkown command");
                    break;
            }
        }   
        s.close();
        System.out.println("Goodbye!");
    }

    public static void main(String[] args) {
        Connect4CLI cli = new Connect4CLI();
        cli.start();
    }
}
