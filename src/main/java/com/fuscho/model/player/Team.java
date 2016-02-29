package com.fuscho.model.player;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * Créer par mchoraine le 28/02/2016.
 */
@Data
public class Team {
    private List<Player> players = new ArrayList<>();
    private Integer totalScore = 0;

    public Team(Player player, Player player1) {
        players.add(player);
        players.add(player1);
    }

    public boolean isPlayerInTeam(Player player) {
        return players.contains(player);
    }

    public void addToScore(int point) {
        totalScore = totalScore + point;
    }
}
