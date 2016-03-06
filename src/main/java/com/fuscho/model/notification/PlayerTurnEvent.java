package com.fuscho.model.notification;

import com.fuscho.model.card.Card;
import lombok.Getter;

import java.util.List;

/**
 * Créer par mchoraine le 05/03/2016.
 */
public class PlayerTurnEvent implements Event {

    @Getter
    private  List<Card> possibleMoves;

    public PlayerTurnEvent(List<Card> possibleMoves) {
        this.possibleMoves = possibleMoves;
    }
}
