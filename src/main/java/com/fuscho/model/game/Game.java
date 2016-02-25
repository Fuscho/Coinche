package com.fuscho.model.game;

import com.fuscho.model.card.Card;
import com.fuscho.model.card.CardPack;
import com.fuscho.model.player.Player;

import java.util.List;

public class Game {

    private Player player1;
    private Player player2;
    private Player player3;
    private Player player4;
    private CardPack cardPack;
    private Score score;

    public Game(){
        this.cardPack = new CardPack();
        this.score = new Score();
    }

    public void addPlayer(Player player){
        if(player1 == null){
            player1 = player;
        } else if(player2 == null){
            player2 = player;
        } else if(player3 == null){
            player3 = player;
        } else if(player4 == null){
            player4 = player;
        }
    }

    public void dealCards(){
        List<Card> cards = cardPack.dealThreeCards();
        player1.addCards(cards);
        player2.addCards(cardPack.dealThreeCards());
        player3.addCards(cardPack.dealThreeCards());
        player4.addCards(cardPack.dealThreeCards());
        player1.addCards(cardPack.dealTwoCards());
        player2.addCards(cardPack.dealTwoCards());
        player3.addCards(cardPack.dealTwoCards());
        player4.addCards(cardPack.dealTwoCards());
        player1.addCards(cardPack.dealThreeCards());
        player2.addCards(cardPack.dealThreeCards());
        player3.addCards(cardPack.dealThreeCards());
        player4.addCards(cardPack.dealThreeCards());
    }

    public void launchGame() {
        this.cardPack.shuffleCards();
        this.dealCards();
    }
}
