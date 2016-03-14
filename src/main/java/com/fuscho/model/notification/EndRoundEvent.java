package com.fuscho.model.notification;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Créer par mchoraine le 05/03/2016.
 */
@AllArgsConstructor
public class EndRoundEvent implements Event {

    @Getter
    private Integer bidderScore;

    @Getter
    private Integer otherScore;
}
