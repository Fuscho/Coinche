package com.fuscho.model.player;

import com.fuscho.model.card.Card;
import com.fuscho.model.game.TurnRound;

/**
 * Créer par mchoraine le 26/02/2016.
 */
public class HumanPlayer extends Player {

    public HumanPlayer(String name) {
        this.setName(name);
    }

    @Override
    public Card getRandomCard(TurnRound currentTurn) {
        return null;
    }
}
