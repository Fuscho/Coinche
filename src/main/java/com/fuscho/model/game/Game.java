package com.fuscho.model.game;

import com.fuscho.model.card.CardPack;
import com.fuscho.model.player.Player;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Slf4j
public class Game {

    private List<Player> players;
    private CardPack cardPack;
    private Score score;
    private RoundGame currentRound;
    private static Game INSTANCE;

    public static Game getInstance(){
        return INSTANCE;
    }

    public Game(){
        this.cardPack = new CardPack();
        this.score = new Score();
        INSTANCE = this;
    }

    public void addPlayer(Player player){
        if(players == null){
            players = Arrays.asList(new Player[4]);
        }
        int position = players.indexOf(null);
        players.set(position, player);
    }

    public List<Player> getPlayers(){
        return players;
    }

    public void launchGame() {
        players.stream().forEach(player -> player.setPartner(players.get((players.indexOf(player) + 2 ) % 4)));
        this.cardPack.shuffleCards();
    }

    public void dealCards(){
        players.stream().forEach(player -> player.addCards(cardPack.dealThreeCards()));
        players.stream().forEach(player -> player.addCards(cardPack.dealTwoCards()));
        players.stream().forEach(player -> player.addCards(cardPack.dealThreeCards()));
    }

    public Player getNextPlayer(Player player){
        int playerPosition = players.indexOf(player);
        if(playerPosition == 3){
            playerPosition = 0;
        } else {
            playerPosition++;
        }
        return players.get(playerPosition);
    }

    public RoundGame startRound(){
        this.dealCards();
        currentRound = new RoundGame();
        return currentRound;
    }

    public void endRound() {
        Integer totalScore = currentRound.countScore();
        if(totalScore >= currentRound.getContractRound().getAskedPoint().getNbPoint()){
            log.info("Gagné");
        } else {
            log.info("Perdu");
        }
        players.stream().forEach(player -> cardPack.addCards(player.getCardsWin()));
    }

    public RoundGame getCurrentRound() {
        return currentRound;
    }

}
