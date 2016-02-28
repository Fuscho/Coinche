package com.fuscho.model.game;

import com.fuscho.model.player.Player;
import com.fuscho.model.player.Team;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * Créer par mchoraine le 25/02/2016.
 */
@Data
public class Score {
    private List<Team> teams = new ArrayList<>();

    public void addTeam(Team team) {
        teams.add(team);
    }

    public void roundWin(Player bidder, ContractPoint askedPoint, Integer totalScore) {
        Team playerTeam = getPlayerTeam(bidder);
        playerTeam.addToScore(askedPoint.getNbPoint() + totalScore);
    }

    private Team getPlayerTeam(Player player) {
        return teams.stream().filter(team -> team.isPlayerInTeam(player)).findFirst().get();
    }
}
