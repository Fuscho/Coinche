package com.fuscho.model.game;

import com.fuscho.model.card.Card;
import com.fuscho.model.card.SuitCard;
import com.fuscho.model.player.Player;
import com.fuscho.operation.Rule;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * Créer par mchoraine le 25/02/2016.
 */
@Slf4j
@Data
public class TurnRound {

    private List<Card> cardsOnTable = new ArrayList<>();
    private SuitCard suitAsked;
    private SuitCard trumpSuit;
    private Player winning;
    private Card masterCard;
    private Player playerTurn;

    public TurnRound(Player player, SuitCard trumpSuit){
        this.playerTurn = player;
        this.trumpSuit = trumpSuit;
    }

    public void play(Player player, Card card){
        if(player.equals(playerTurn)){
            if(cardsOnTable.size() == 0){
                cardsOnTable.add(card);
                suitAsked = card.getSuit();
                masterCard = card;
            } else {
                cardsOnTable.add(card);
                masterCard = Rule.getMasterCard(cardsOnTable, trumpSuit);
            }
            if(card.equals(masterCard)){
                winning = player;
            }
        } else {
            log.error("Not this player turn");
        }
    }

    public Boolean isPartenaireMaster(Player player) {
        if(winning == null){
            return false;
        }
        return winning.equals(player.getPartner());
    }

    public void winnerCollectCards() {
        winning.addCardsWin(cardsOnTable);
    }
}
