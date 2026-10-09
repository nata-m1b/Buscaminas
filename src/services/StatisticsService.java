/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;
import models.Player;
import repositories.PlayerRepository;

import java.util.List;
/**
 *
 * @author nmart
 */

public class StatisticsService {

    private final PlayerRepository playerRepository;

    public StatisticsService(
            PlayerRepository playerRepository) {

        this.playerRepository =
                playerRepository;
    }

    public List<Player> getAllPlayers() {

        return playerRepository
                .getData()
                .getPlayers();
    }

    public double getWinPercentage(Player player) {

        return player.getWinPercentage();
    }

    public long getBestTime(Player player) {

        return player.getBestTime();
    }

    public double getAverageTime(Player player) {

        return player.getAverageTime();
    }
}
