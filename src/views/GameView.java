package views;

import models.Board;
import models.Cell;
import models.Game;
import services.GameService;
import java.util.Scanner;

public class GameView {

    private Scanner scanner;
    private GameService gameService;

    public GameView() {
        this.scanner = new Scanner(System.in);
        this.gameService = new GameService();
    }

    public void displayBoard(Game game) {
        Board board = game.getBoard();
        Cell[][] cells = board.getCells();

        System.out.println("\n");
        printColumnHeaders(board);

        for (int i = 0; i < board.getRows(); i++) {
            System.out.print(String.format("%2d | ", i));

            for (int j = 0; j < board.getColumns(); j++) {
                Cell cell = cells[i][j];
                System.out.print(formatCell(cell) + " ");
            }

            System.out.println();
        }

        System.out.println("\nMinas restantes: " + countRemainingMines(board));
        System.out.println("Tiempo: " + (game.getElapsedTime() / 1000) + "s");
    }

    private void printColumnHeaders(Board board) {
        System.out.print("    ");
        for (int j = 0; j < board.getColumns(); j++) {
            System.out.print(String.format("%2d ", j));
        }
        System.out.println();
        System.out.println("   " + "-".repeat(board.getColumns() * 3 + 1));
    }

    private String formatCell(Cell cell) {
        if (cell.isFlagged()) {
            return "o";
        }

        if (!cell.isRevealed()) {
            return "?";
        }

        if (cell.isMine()) {
            return "x";
        }

        int adjacentMines = cell.getAdjacentMines();
        return adjacentMines == 0 ? " " : String.valueOf(adjacentMines);
    }

    public void playGame(Game game) {
        while (!game.isFinished()) {
            displayBoard(game);

            String command = getPlayerInput();

            if (command.equalsIgnoreCase("quit")) {
                System.out.println("Saliendo del juego...");
                break;
            }

            processCommand(game, command);

            if (gameService.isGameOver(game)) {
                game.finish(false);
                displayBoard(game);
                System.out.println("\n!GAME OVER! Pisaste una mina");
            } else if (gameService.isGameWon(game)) {
                game.finish(true);
                displayBoard(game);
                System.out.println("\n!GANASTE!");
            }
        }
    }

    private void processCommand(Game game, String command) {
        String[] parts = command.trim().split(" ");

        if (parts.length < 3) {
            System.out.println("Comando invalido. Usa: 'r fila columna' o 'f fila columna'");
            return;
        }

        try {
            int row = Integer.parseInt(parts[1]);
            int column = Integer.parseInt(parts[2]);

            if (parts[0].equalsIgnoreCase("r")) {
                gameService.revealCell(game.getBoard(), row, column);
            } else if (parts[0].equalsIgnoreCase("f")) {
                gameService.toggleFlag(game.getBoard(), row, column);
            } else {
                System.out.println("Comando desconocido. Usa 'r' para revelar o 'f' para bandera");
            }
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            System.out.println("Entrada invalida. Usa: 'r fila columna' o 'f fila columna'");
        }
    }

    private String getPlayerInput() {
        System.out.print("\nComando (r fila columna / f fila columna / quit): ");
        return scanner.nextLine();
    }

    private int countRemainingMines(Board board) {
        Cell[][] cells = board.getCells();
        int remaining = board.getMines();

        for (int i = 0; i < board.getRows(); i++) {
            for (int j = 0; j < board.getColumns(); j++) {
                if (cells[i][j].isMine() && cells[i][j].isFlagged()) {
                    remaining--;
                }
            }
        }

        return remaining;
    }
}
