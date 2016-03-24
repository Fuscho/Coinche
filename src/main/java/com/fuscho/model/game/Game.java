package com.fuscho.model.game;

import com.fuscho.model.card.CardPack;
import com.fuscho.model.player.IAPlayer;
import com.fuscho.model.player.Player;
import com.fuscho.model.player.Team;
import com.fuscho.model.player.TeamManager;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class Game {

    private List<Player> players;
    private CardPack cardPack;
    private TeamManager teamManager;
    private RoundGame currentRound;
    private static Game INSTANCE;

    public static Game getInstance(){
        return INSTANCE;
    }

    public Game(List<Player> users){
        this.cardPack = new CardPack();
        this.teamManager = new TeamManager();
        this.players = users;
        INSTANCE = this;
    }

    /**
     * Start the game :
     * Create teams and shuffle cards
     */
    public void launchGame() {
        Team team1 = new Team(players.get(0), players.get(2));
        Team team2 = new Team(players.get(1), players.get(3));
        this.teamManager.addTeam(team1);
        this.teamManager.addTeam(team2);
        this.cardPack.shuffleCards();
    }

    /**
     * Start a new round :
     * Deals the cards and start a round
     * @return the new round
     */
    public RoundGame startRound(){
        this.dealCards();
        //When started round all team has a roundScore equals to 0
        for (Team team: teamManager.getTeams()){
            team.setRoundScore(0);
        }

        currentRound = new RoundGame();
        return currentRound;
    }

    /**
     * End the round :
     * Count the points
     * Put the cards together
     */
    public void endRound() {
        /*Integer totalScore = currentRound.countScore();
        if(totalScore >= currentRound.getContractRound().getAskedPoint().getNbPoint()){
            log.info("Gagné");
            teamManager.roundWin(currentRound.getContractRound().getBidder(), currentRound.getContractRound().getAskedPoint(), totalScore);
        } else {
            log.info("Perdu");
            teamManager.roundLose(currentRound.getContractRound().getBidder(), currentRound.getContractRound().getAskedPoint(), totalScore);
        }*/
        players.stream().forEach(player -> cardPack.addCards(player.getCardsWin()));
        players.stream().forEach(player -> player.setCardsWin(new ArrayList<>()));
    }

    /**
     * Get the players in game
     * @return the players in game
     */
    public List<Player> getPlayers(){
        return players;
    }

    /**
     * Get the player partner
     * @param player player who need to know his partner
     * @return the partner
     */
    public Player getPlayerPartner(Player player){
        return teamManager.getPlayerTeam(player).getPlayers().stream().filter(p -> !p.equals(player)).findFirst().get();
    }

    /**
     * Deal 3*2*3 cards to each players
     */
    public void dealCards(){
        players.stream().forEach(player -> player.addCards(cardPack.dealThreeCards()));
        players.stream().forEach(player -> player.addCards(cardPack.dealTwoCards()));
        players.stream().forEach(player -> player.addCards(cardPack.dealThreeCards()));

        //Init possible other player possibleMoves
        for(Player player: players){
            if(player instanceof IAPlayer){
                List<Player> otherPlayers = players.stream().filter(otherPlayer-> otherPlayer!=player).collect(Collectors.toList());
                ((IAPlayer) player).initPossibleMoves(otherPlayers);
            }
        }
    }



    /**
     * Get the next player who have to play
     * @param player the player who just played
     * @return player who have to play
     */
    public Player getNextPlayer(Player player){
        int playerPosition = players.indexOf(player);
        if(playerPosition == 3){
            playerPosition = 0;
        } else {
            playerPosition++;
        }
        return players.get(playerPosition);
    }

    /**
     * Get the current round
     * @return the round
     */
    public RoundGame getCurrentRound() {
        return currentRound;
    }

    /**
     * Get the teamManager
     * @return teamManager
     */
    public TeamManager getTeamManager(){
        return teamManager;
    }

    public Player getPlayer(String pseudo) {
        return players.stream().filter(player -> player.getName().equals(pseudo)).findFirst().get();
    }
}
