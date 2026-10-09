/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package presenters;

import models.Cell;
import models.Difficulty;
import models.Game;
import models.Player;
import repositories.PlayerRepository;

public class GamePresenter {

    private final PlayerRepository playerRepository;

    private Player currentPlayer;
    private Game currentGame;

    public GamePresenter(
            PlayerRepository playerRepository) {

        this.playerRepository = playerRepository;
    }

    public void startGame(
            Player player,
            Difficulty difficulty) {

        currentPlayer = player;

        currentGame = new Game(
                generateGameId(),
                player.getId(),
                difficulty
        );
    }

    public void revealCell(int row, int column) {

        Cell cell = currentGame
                .getBoard()
                .getCells()[row][column];

        if (cell.isFlagged() || cell.isRevealed()) {
            return;
        }

        cell.setRevealed(true);

        // Si es una mina, pierde
        if (cell.isMine()) {

            finishGame(false);
            return;
        }

        // Si despejó todas las casillas necesarias, gana
        if (checkVictory()) {

            finishGame(true);
        }
    }

    public void toggleFlag(
            int row,
            int column) {

        Cell cell = currentGame
                .getBoard()
                .getCells()[row][column];

        if (!cell.isRevealed()) {

            cell.setFlagged(
                    !cell.isFlagged()
            );
        }
    }

    private boolean checkVictory() {

        for (Cell[] row :
                currentGame.getBoard().getCells()) {

            for (Cell cell : row) {

                if (!cell.isMine()
                        && !cell.isRevealed()) {

                    return false;
                }
            }
        }

        return true;
    }

    private void finishGame(boolean victory) {

        currentGame.finish(victory);

        long time =
                currentGame.getElapsedTime();

        if (victory) {

            currentPlayer.registerVictory(time);

        } else {

            currentPlayer.registerDefeat(time);
        }

        playerRepository.update(currentPlayer);
    }

    private int generateGameId() {

        return (int)
                (System.currentTimeMillis() % 100000);
    }

    public Game getCurrentGame() {
        return currentGame;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }
}
