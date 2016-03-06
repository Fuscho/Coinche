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
    private Integer roundScore = 0;

    public Team(Player player, Player player1) {
        players.add(player);
        players.add(player1);
    }

    public boolean isPlayerInTeam(Player player) {
        return players.contains(player);
    }

    public void addToTotalScore(int point) {
        totalScore += point;
    }

    public void addToRoundScore(int point) {
        roundScore += point;
    }
}
