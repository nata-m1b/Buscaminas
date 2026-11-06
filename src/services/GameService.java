package services;

import models.Board;
import models.Cell;
import models.Game;

public class GameService {

    public void revealCell(Board board, int row, int column) {
        if (!isValidPosition(board, row, column)) {
            return;
        }

        Cell cell = board.getCells()[row][column];

        if (cell.isRevealed() || cell.isFlagged()) {
            return;
        }

        cell.setRevealed(true);

        if (cell.getAdjacentMines() == 0 && !cell.isMine()) {
            revealAdjacentCells(board, row, column);
        }
    }

    private void revealAdjacentCells(Board board, int row, int column) {
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                int newRow = row + i;
                int newColumn = column + j;

                if (isValidPosition(board, newRow, newColumn)) {
                    Cell cell = board.getCells()[newRow][newColumn];
                    if (!cell.isRevealed() && !cell.isFlagged()) {
                        revealCell(board, newRow, newColumn);
                    }
                }
            }
        }
    }

    public void toggleFlag(Board board, int row, int column) {
        if (!isValidPosition(board, row, column)) {
            return;
        }

        Cell cell = board.getCells()[row][column];

        if (cell.isRevealed()) {
            return;
        }

        cell.setFlagged(!cell.isFlagged());
    }

    public boolean isGameOver(Game game) {
        Board board = game.getBoard();
        Cell[][] cells = board.getCells();

        for (int i = 0; i < board.getRows(); i++) {
            for (int j = 0; j < board.getColumns(); j++) {
                if (cells[i][j].isMine() && cells[i][j].isRevealed()) {
                    return true;
                }
            }
        }

        return false;
    }

    public boolean isGameWon(Game game) {
        Board board = game.getBoard();
        Cell[][] cells = board.getCells();

        int minesCount = 0;
        int revealedCount = 0;

        for (int i = 0; i < board.getRows(); i++) {
            for (int j = 0; j < board.getColumns(); j++) {
                Cell cell = cells[i][j];

                if (cell.isMine()) {
                    minesCount++;
                } else if (cell.isRevealed()) {
                    revealedCount++;
                }
            }
        }

        return revealedCount == (board.getRows() * board.getColumns() - minesCount);
    }

    private boolean isValidPosition(Board board, int row, int column) {
        return row >= 0 && row < board.getRows() &&
               column >= 0 && column < board.getColumns();
    }
}
