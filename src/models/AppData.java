/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author nmart
 */

public class AppData {

    private int lastPlayerId;

    private List<Player> players;
    private List<GameRecord> games;

    public AppData() {

        players = new ArrayList<>();
        games = new ArrayList<>();
    }

    public int getLastPlayerId() {
        return lastPlayerId;
    }

    public void setLastPlayerId(int lastPlayerId) {
        this.lastPlayerId = lastPlayerId;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public List<GameRecord> getGames() {
        return games;
    }
}
