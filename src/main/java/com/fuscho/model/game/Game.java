package com.fuscho.model.game;

import com.fuscho.model.card.CardPack;
import com.fuscho.model.player.Player;

public class Game {

    private Player player1;
    private Player player2;
    private Player player3;
    private Player player4;
    private CardPack cardPack;
    private Score score;

    public void dealCards(){
        player1.getCards().addAll(cardPack.dealThreeCards());
        player2.getCards().addAll(cardPack.dealThreeCards());
        player3.getCards().addAll(cardPack.dealThreeCards());
        player4.getCards().addAll(cardPack.dealThreeCards());
        player1.getCards().addAll(cardPack.dealTwoCards());
        player2.getCards().addAll(cardPack.dealTwoCards());
        player3.getCards().addAll(cardPack.dealTwoCards());
        player4.getCards().addAll(cardPack.dealTwoCards());
        player1.getCards().addAll(cardPack.dealThreeCards());
        player2.getCards().addAll(cardPack.dealThreeCards());
        player3.getCards().addAll(cardPack.dealThreeCards());
        player4.getCards().addAll(cardPack.dealThreeCards());
    }
}
