/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package presenters;

import models.Player;
import services.StatisticsService;
import views.StatisticsView;

/**
 *
 * @author nmart
 */
public class StatisticsPresenter {

    private final StatisticsView view;
    private final StatisticsService service;

    public StatisticsPresenter(
            StatisticsView view,
            StatisticsService service) {

        this.view = view;
        this.service = service;
    }

    public void loadStatistics() {

        for (Player player :
                service.getAllPlayers()) {

            view.addPlayerRow(
                    player.getName(),
                    player.getGamesPlayed(),
                    player.getVictories(),
                    player.getDefeats(),
                    player.getWinPercentage(),
                    player.getBestTime()
            );
        }
    }
}
