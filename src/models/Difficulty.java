/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

/**
 *
 * @author nmart
 */

public enum Difficulty {

    EASY(10, 6, 10),
    MEDIUM(20, 8, 35),
    HARD(30, 16, 75);

    private final int rows;
    private final int columns;
    private final int mines;

    Difficulty(int rows, int columns, int mines) {
        this.rows = rows;
        this.columns = columns;
        this.mines = mines;
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
