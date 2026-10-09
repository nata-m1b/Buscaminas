/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package presenters;

import models.Player;
import repositories.PlayerRepository;
import views.PlayerView;

/**
 *
 * @author nmart
 */

public class PlayerPresenter {

    private final PlayerView view;
    private final PlayerRepository repository;

    public PlayerPresenter(
            PlayerView view,
            PlayerRepository repository) {

        this.view = view;
        this.repository = repository;
    }

    public void showLastPlayer() {

        Player lastPlayer =
                repository.getLastPlayer();

        if (lastPlayer != null) {

            view.showLastPlayer(lastPlayer.getName());

        } else {

            view.showNoLastPlayer();
        }
    }

    public Player useLastPlayer() {

        Player player =
                repository.getLastPlayer();

        repository.setLastPlayer(player);

        return player;
    }

    public Player createOrFindPlayer(String name) {

        Player player =
                repository.findByName(name);

        if (player == null) {

            player = new Player(
                    generateId(),
                    name
            );

            repository.save(player);
        }

        repository.setLastPlayer(player);

        return player;
    }

    private int generateId() {

        return repository
                .getData()
                .getPlayers()
                .size() + 1;
    }
}
