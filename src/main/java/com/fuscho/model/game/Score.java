package com.fuscho.model.game;

import com.fuscho.model.player.Player;
import com.fuscho.model.player.Team;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * Créer par mchoraine le 25/02/2016.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Score {
    private List<Team> teams = new ArrayList<>();

    public void addTeam(Team team) {
        teams.add(team);
    }

    public void roundWin(Player bidder, ContractPoint askedPoint, Integer totalScore) {
        Team playerTeam = getPlayerTeam(bidder);
        playerTeam.addToScore(askedPoint.getNbPoint() + totalScore);
    }

    public void roundLose(Player bidder, ContractPoint askedPoint, Integer totalScore) {
        Team playerTeam = getAdversaryTeam(bidder);
        playerTeam.addToScore(askedPoint.getNbPoint() + totalScore);
    }

    public Team getPlayerTeam(Player player) {
        return teams.stream().filter(team -> team.isPlayerInTeam(player)).findFirst().get();
    }

    public Team getAdversaryTeam(Player player) {
        return teams.stream().filter(team -> !team.isPlayerInTeam(player)).findFirst().get();
    }
}
