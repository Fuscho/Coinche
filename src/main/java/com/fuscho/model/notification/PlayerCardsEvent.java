package com.fuscho.model.notification;

import com.fuscho.model.card.Card;
import lombok.Data;

import java.util.List;

/**
 * Créer par mchoraine le 24/03/2016.
 */
@Data
public class PlayerCardsEvent implements Event {

    private List<Card> cards;

    public PlayerCardsEvent(List<Card> cards) {
        this.cards = cards;
    }
}
