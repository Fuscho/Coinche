package com.fuscho.model.game;

import com.fuscho.model.card.Card;
import com.fuscho.model.card.SuitCard;
import com.fuscho.model.card.ValueCard;
import com.fuscho.model.player.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * Créer par mchoraine le 25/02/2016.
 */
public class RoundGame {
    private ContractRound contractRound;
    private List<Card> cardsWinTeamA;
    private List<Card> cardsWinTeamB;
    private TurnRound currentTurn;

    public RoundGame(){

    }

    public void playerPlayCard(Player player, Card card) {
        currentTurn.play(player, card);
    }

    public void playerBid(Player player, ContractRound.ContractPoint bidPoint, SuitCard suit) {
        this.contractRound = ContractRound.builder()
                .bidder(player)
                .askedPoint(bidPoint)
                .suit(suit)
                .build();
    }

    public void startTurn(Player player) {
        currentTurn = new TurnRound(player);
    }
}
