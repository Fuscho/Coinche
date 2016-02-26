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

    public Game(){
        this.cardPack = new CardPack();
        this.score = new Score();
    }

    public void addPlayer(Player player){
        if(players == null){
            players = Arrays.asList(new Player[4]);
        }
        int position = players.indexOf(null);
        players.set(position, player);
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
        return new RoundGame();
    }
}
