package com.fuscho.model.player;

import com.fuscho.model.game.ContractPoint;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Créer par mchoraine le 25/02/2016.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeamManager {
    private List<Team> teams = new ArrayList<>();

    public void addTeam(Team team) {
        teams.add(team);
    }

    public List<Team> getTeams(){
        return this.teams;
    }

    public void roundWin(Player bidder, ContractPoint askedPoint, Integer totalScore) {
        Team playerTeam = getPlayerTeam(bidder);
        playerTeam.addToTotalScore(askedPoint.getNbPoint() + totalScore);
    }

    public void roundLose(Player bidder, ContractPoint askedPoint, Integer totalScore) {
        Team playerTeam = getAdversaryTeam(bidder);
        playerTeam.addToTotalScore(askedPoint.getNbPoint() + totalScore);
    }

    public Team getPlayerTeam(Player player) {
        return teams.stream().filter(team -> team.isPlayerInTeam(player)).findFirst().get();
    }

    public Team getAdversaryTeam(Player player) {
        Optional<Team> first = teams.stream().filter(team -> !team.isPlayerInTeam(player)).findFirst();
        return first.get();
    }

    public Player getPartner(Player player){
        Team teamPlayer = this.getPlayerTeam(player);
        return teamPlayer.getPlayers().stream().filter(player1 -> player1 != player).findFirst().get();
    }

    public Boolean getIfAdversary(Player player, Player otherPlayer){
        Team teamAdversary = this.getAdversaryTeam(player);
        return teamAdversary.getPlayers().contains(otherPlayer);
    }
}
