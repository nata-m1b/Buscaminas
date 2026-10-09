/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

/**
 *
 * @author nmart
 */

public class GameRecord {

    private int id;
    private int playerId;

    private Difficulty difficulty;

    private boolean victory;
    private long time;

    public GameRecord() {
    }

    public GameRecord(
            int id,
            int playerId,
            Difficulty difficulty,
            boolean victory,
            long time) {

        this.id = id;
        this.playerId = playerId;
        this.difficulty = difficulty;
        this.victory = victory;
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public int getPlayerId() {
        return playerId;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public boolean isVictory() {
        return victory;
    }

    public long getTime() {
        return time;
    }
}