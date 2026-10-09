/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repositories;

import models.AppData;
import models.Player;
/**
 *
 * @author nmart
 */
public class PlayerRepository {

    private final JsonDataRepository dataRepository;
    private final AppData data;

    public PlayerRepository() {

        dataRepository = new JsonDataRepository();
        data = dataRepository.load();
    }

    public Player findById(int id) {

        return data.getPlayers()
                .stream()
                .filter(player -> player.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public Player findByName(String name) {

        return data.getPlayers()
                .stream()
                .filter(player ->
                        player.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElse(null);
    }

    public Player save(Player player) {

        data.getPlayers().add(player);

        dataRepository.save(data);

        return player;
    }

    public void update(Player player) {

        dataRepository.save(data);
    }

    public Player getLastPlayer() {

        return findById(data.getLastPlayerId());
    }

    public void setLastPlayer(Player player) {

        data.setLastPlayerId(player.getId());

        dataRepository.save(data);
    }

    public AppData getData() {
        return data;
    }
}