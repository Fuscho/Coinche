package com.fuscho.model.game;

import com.fuscho.model.card.Card;
import com.fuscho.model.card.SuitCard;
import com.fuscho.model.player.Player;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * Créer par mchoraine le 25/02/2016.
 */
@Data
@Slf4j
public class RoundGame {
    private ContractRound contractRound;
    private TurnRound currentTurn;

    public RoundGame(){}

    public void playerPlayCard(Player player, Card card) {
        log.info("player play : {}", card);
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

    public void nextPlayer(Game game) {
        if(currentTurn.getCardsOnTable().size() < 4){
            currentTurn.setPlayerTurn(game.getNextPlayer(currentTurn.getPlayerTurn()));
        } else {
            currentTurn.winnerCollectCards();
            log.info("We have a winner : ", currentTurn.getMasterCard() );
            startTurn(currentTurn.getWinning());
        }
    }
}
