/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import java.util.Random;
/**
 *
 * @author nmart
 */
public class Board {

    private int rows;
    private int columns;
    private int mines;

    private Cell[][] cells;

    public Board() {
    }

    public Board(Difficulty difficulty) {

        this.rows = difficulty.getRows();
        this.columns = difficulty.getColumns();
        this.mines = difficulty.getMines();

        createBoard();
        placeMines();
        calculateNumbers();
    }

    private void createBoard() {

        cells = new Cell[rows][columns];

        for (int i = 0; i < rows; i++) {

            for (int j = 0; j < columns; j++) {
                cells[i][j] = new Cell();
            }
        }
    }

    private void placeMines() {

        Random random = new Random();

        int placed = 0;

        while (placed < mines) {

            int row = random.nextInt(rows);
            int column = random.nextInt(columns);

            if (!cells[row][column].isMine()) {

                cells[row][column].setMine(true);
                placed++;
            }
        }
    }

    private void calculateNumbers() {

        for (int row = 0; row < rows; row++) {

            for (int column = 0; column < columns; column++) {

                if (!cells[row][column].isMine()) {

                    int count = countAdjacentMines(row, column);

                    cells[row][column].setAdjacentMines(count);
                }
            }
        }
    }

    private int countAdjacentMines(int row, int column) {

        int count = 0;

        for (int i = -1; i <= 1; i++) {

            for (int j = -1; j <= 1; j++) {

                int newRow = row + i;
                int newColumn = column + j;

                if (isValidPosition(newRow, newColumn)
                        && cells[newRow][newColumn].isMine()) {

                    count++;
                }
            }
        }

        return count;
    }

    private boolean isValidPosition(int row, int column) {

        return row >= 0 &&
               row < rows &&
               column >= 0 &&
               column < columns;
    }

    public Cell[][] getCells() {
        return cells;
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

    public int getMines() {
        return mines;
    }
}
