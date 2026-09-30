/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package buscaminas.models;

/**
 *
 * @author nmart
 */
public class Player {
    private int id;
    private String name;
    private int gamesPlayed;
    private int victories;
    private int defeats;
    private long bestTime;
    private long totalTime;

    public Player() {
    }

    public Player(int id, String name) {
        this.id = id;
        this.name = name;
        this.bestTime = 0;
    }
    
    public void registerVictory(long time){
        gamesPlayed++;
        victories++;
        totalTime += time;
        if (bestTime == 0 || time < bestTime) {
            bestTime = time;
        }
    }
    
    public void registerDefeat(long time){
        gamesPlayed++;
        defeats++;
        totalTime += time;
    }
    
    public double getWinPercentage(){
        if (gamesPlayed == 0) {
            return 0;
        }
        return ((double) victories/gamesPlayed)*100;
    }
    
    public double getAverageTime(){
            if (gamesPlayed == 0) {
                return 0;
            }
            return (double) totalTime/gamesPlayed;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getGamesPlayed() {
        return gamesPlayed;
    }

    public void setGamesPlayed(int gamesPlayed) {
        this.gamesPlayed = gamesPlayed;
    }

    public int getVictories() {
        return victories;
    }

    public void setVictories(int victories) {
        this.victories = victories;
    }

    public int getDefeats() {
        return defeats;
    }

    public void setDefeats(int defeats) {
        this.defeats = defeats;
    }

    public long getBestTime() {
        return bestTime;
    }

    public void setBestTime(long bestTime) {
        this.bestTime = bestTime;
    }

    public long getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(long totalTime) {
        this.totalTime = totalTime;
    }
    
    
    
    
    
    
    
}
