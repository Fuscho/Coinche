package com.fuscho.model.notification;

import com.fuscho.model.player.Player;
import com.fuscho.rest.resources.PlayerResource;
import com.fuscho.service.AuthentificationService;
import lombok.Data;

import java.util.List;

/**
 * Créer par mchoraine le 22/03/2016.
 */
@Data
public class GameStartedEvent implements Event {

    private String idGame;
    private PlayerResource topPlayer;
    private PlayerResource leftPlayer;
    private PlayerResource rightPlayer;
    private PlayerResource currentPlayer;

    public GameStartedEvent(String idGame, String currentPlayerName, List<Player> players){
        this.idGame = idGame;
        Player currentplayer = players.stream().filter(player -> player.getName().equals(currentPlayerName)).findFirst().get();
        int indexCurrentPlayer = players.indexOf(currentplayer);
        this.currentPlayer = PlayerResource.createResourceFromPlayer(currentplayer);
        this.leftPlayer = PlayerResource.createResourceFromPlayer(players.get((indexCurrentPlayer + 1 ) % 4));
        this.topPlayer = PlayerResource.createResourceFromPlayer(players.get((indexCurrentPlayer + 2 ) % 4));
        this.rightPlayer = PlayerResource.createResourceFromPlayer(players.get((indexCurrentPlayer + 3 ) % 4));
    }
}
