package com.fuscho.model.player;

import com.fuscho.model.card.Card;
import com.fuscho.model.game.TurnRound;
import com.fuscho.operation.Rule;

import java.util.List;

/**
 * Créer par mchoraine le 26/02/2016.
 */
public class IAPlayer extends Player{

    public IAPlayer(String name) {
        this.setName(name);
    }

    @Override
    public Card getRandomCard(TurnRound currentTurn) {
        List<Card> possibleMoves = Rule.getPossibleMoves(this.getCards(), currentTurn.getSuitAsked(), currentTurn.getTrumpSuit(), currentTurn.getMasterCard(), currentTurn.isPartenaireMaster(this));
        return possibleMoves.get(0);
    }
}
