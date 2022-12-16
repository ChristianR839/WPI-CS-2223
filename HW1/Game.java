import jdk.internal.cmm.SystemResourcePressureImpl;

import java.awt.*;
import java.util.Locale;
import java.util.Random;
import java.util.Scanner;

public class Game {

    private final Board board;
    private final Scanner scanner;
    private String input;
    private boolean player; // true = human, false = cpu
    private int[] wins = {0, 0};

    private final Random rand;

    public Game(Board board) {
        this.board = board;
        this.scanner = new Scanner(System.in);
        this.rand = new Random();
    }

    public void play() {

        System.out.println("Welcome to N̶i̶m̶ Double Trouble!");
        System.out.println("This is a best of 5 game.");

        boolean rounds = true;
        while (rounds) {
            boolean decideFirst = true;
            while (decideFirst) {
                System.out.println("Will you go first? (y/n)");
                input = scanner.nextLine();

                switch(input.toLowerCase(Locale.ENGLISH).trim()) {
                    case "y":
                        player = true;
                        decideFirst = false;
                        break;
                    case "n":
                        player = false;
                        decideFirst = false;
                        break;
                    default:
                        System.out.println("Invalid input, try again.");
                        break;
                }
            }

            boolean playing = true;
            while (playing) {
                board.print();
                if (player) humanTurn();
                else cpuTurn();

                if (board.isEmpty()) {
                    int playerInt = player ? 1 : 0;
                    wins[playerInt] += 1;
                    if (wins[playerInt] == 3) winner();
                    roundWinner();
                    playing = false;
                } else {
                    player = !player;
                }
            }
        }
    }

    private void humanTurn() {
        PieceColor color = PieceColor.GREEN;
        int amt;

        boolean colorPick = true;
        while (colorPick) {
            System.out.println("Which color do you want to remove? (g,y,o)");

            input = scanner.nextLine();

            switch (input.toLowerCase().trim()) {
                case "g":
                    color = PieceColor.GREEN;
                    if (board.getRemaining(color) == 0) {
                        System.out.println("Invalid input, try again.");
                        break;
                    }
                    colorPick = false;
                    break;
                case "y":
                    color = PieceColor.YELLOW;
                    if (board.getRemaining(color) == 0) {
                        System.out.println("Invalid input, try again.");
                        break;
                    }
                    colorPick = false;
                    break;
                case "o":
                    color = PieceColor.ORANGE;
                    if (board.getRemaining(color) == 0) {
                        System.out.println("Invalid input, try again.");
                        break;
                    }
                    colorPick = false;
                    break;
                default:
                    System.out.println("Invalid input, try again.");
                    break;
            }
        }

        boolean amtPick = true;
        while (amtPick) {
            System.out.println("How many pieces do you want to remove?");
            input = scanner.nextLine();

            if (!isInt(input)) {
                System.out.println("Invalid input, try again.");
            } else {
                amt = Integer.parseInt(input);

                if (amt > board.getRemaining(color)) {
                    System.out.println("Invalid input, try again.");
                } else {
                    System.out.println("Removing " + amt + " "
                            + color.toString().toLowerCase() + " pieces.");
                    board.remove(color, amt);
                    amtPick = false;
                }
            }
        }
    }

    private void cpuTurn() {
        if (board.getNimSum() != 0) {
            board.nimTurn();
        } else {
            cpuRand();
        }

        board.getNimSum();
    }

    private void cpuRand() {
        PieceColor color = PieceColor.GREEN;
        int amt;

        boolean chooseRand = true;
        while (chooseRand) {
            color = PieceColor.randomColor();
            if (board.getRemaining(color) > 0) {
                chooseRand = false;
            }
        }

        amt = rand.nextInt(board.getRemaining(color)) + 1;

        System.out.println("CPU TURN: Removing " + amt + " " + color.toString().toLowerCase() + " pieces.");
        board.remove(color, amt);
    }

    private void winner() {
        System.out.println();
        System.out.println("You won this round! Score (best of 5):");
        System.out.println("> Human: " + wins[1]);
        System.out.println("> CPU:   " + wins[0]);
        System.out.println();
        if (player) {
            System.out.println("Congratulations! You have won the game!");
        } else {
            System.out.println("The computer has won. Try harder next time!");
        }

        System.out.println();
        System.out.println("This game is based off Nim, a mathematical game originally solved by Charles Leonard Bouton");
        System.exit(0);
    }

    private void roundWinner() {
        System.out.println();
        if (player) {
            System.out.println("You won this round! Score (best of 5):");
            System.out.println("> Human: " + wins[1]);
            System.out.println("> CPU:   " + wins[0]);
        } else {
            System.out.println("The computer won this round! Score (best of 5):");
            System.out.println("> Human: " + wins[1]);
            System.out.println("> CPU:   " + wins[0]);
        }
        System.out.println();
        System.out.println("NEW GAME");
        board.reset();
    }

    private boolean isInt(String s) {
        try {
            Integer.parseInt(s);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }
}
