package com.fuscho.model.player;

import com.fuscho.model.User;
import com.fuscho.model.card.Card;
import com.fuscho.model.game.TurnRound;

/**
 * Créer par mchoraine le 26/02/2016.
 */
public class HumanPlayer extends Player {

    private User user;

    public HumanPlayer(User user) {
        this.user = user;
        this.setName(user.getUsername());
    }

    @Override
    public Card getRandomCard(TurnRound currentTurn) {
        return null;
    }
}
