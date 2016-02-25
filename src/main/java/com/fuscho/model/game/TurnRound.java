package com.fuscho.model.game;

import com.fuscho.model.card.Card;
import com.fuscho.model.card.SuitCard;
import com.fuscho.model.player.Player;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * Créer par mchoraine le 25/02/2016.
 */
@Slf4j
public class TurnRound {

    private List<Card> cardsOnTable = new ArrayList<>();
    private Player winning;
    private SuitCard suitAsked;
    private Player playerTurn;

    public TurnRound(Player player){
        this.playerTurn = player;
    }

    public void play(Player player, Card card){
        if(player.equals(playerTurn)){
            if(cardsOnTable.size() == 0){
                suitAsked = card.getSuit();
            }
            cardsOnTable.add(card);
        }
    }

}
