import models.Difficulty;
import models.Game;
import views.GameView;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        GameView gameView = new GameView();

        System.out.println("=== BUSCAMINAS ===\n");
        System.out.println("Selecciona la dificultad:");
        System.out.println("1. FACIL (10x6 tablero, 10 minas)");
        System.out.println("2. MEDIO (20x8 tablero, 35 minas)");
        System.out.println("3. DIFICIL (30x16 tablero, 75 minas)");
        System.out.print("\nOpcion (1-3): ");

        int choice = 0;
        try {
            choice = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Opcion invalida. Iniciando juego en dificultad FACIL...");
            choice = 1;
        }

        Difficulty difficulty = selectDifficulty(choice);

        System.out.println("\nIniciando juego en dificultad: " + difficulty);
        System.out.println("\nControles:");
        System.out.println("  r fila columna - Revelar una celda");
        System.out.println("  f fila columna - Colocar/quitar bandera");
        System.out.println("  quit - Salir del juego\n");

        Game game = new Game(1, 1, difficulty);
        gameView.playGame(game);

        scanner.close();
    }

    private static Difficulty selectDifficulty(int choice) {
        switch (choice) {
            case 1:
                return Difficulty.EASY;
            case 2:
                return Difficulty.MEDIUM;
            case 3:
                return Difficulty.HARD;
            default:
                return Difficulty.EASY;
        }
    }
}
