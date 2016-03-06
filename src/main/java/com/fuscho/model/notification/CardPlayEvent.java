package com.fuscho.model.notification;

import com.fuscho.model.card.Card;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Créer par mchoraine le 05/03/2016.
 */
@AllArgsConstructor
public class CardPlayEvent implements Event {

    @Getter
    private Card cardPlay;

    @Getter
    private Integer playerPosition;
}
