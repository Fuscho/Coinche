package com.fuscho.model.game;

import com.fuscho.model.card.Card;
import com.fuscho.model.card.SuitCard;
import com.fuscho.model.player.Player;
import lombok.Data;

import java.util.List;

/**
 * Créer par mchoraine le 25/02/2016.
 */
@Data
public class RoundGame {
    private ContractRound contractRound;
    private List<Card> cardsWinTeamA;
    private List<Card> cardsWinTeamB;
    private TurnRound currentTurn;

    public RoundGame(){

    }

    public void playerPlayCard(Player player, Card card) {
        currentTurn.play(player, card);
        player.playThisCard(card);
    }

    public void playerBid(Player player, ContractRound.ContractPoint bidPoint, SuitCard suit) {
        this.contractRound = ContractRound.builder()
                .bidder(player)
                .askedPoint(bidPoint)
                .trumpSuit(suit)
                .build();
    }

    public void startTurn(Player player) {
        currentTurn = new TurnRound(player, contractRound.getTrumpSuit());
    }

    public void nextPlayer(Player player) {
        currentTurn.setPlayerTurn(player);
    }
}
