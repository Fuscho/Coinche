package com.fuscho.model.notification;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Créer par mchoraine le 05/03/2016.
 */
@AllArgsConstructor
public class RoundResultEvent implements Event {

    @Getter
    private Boolean success;

    @Getter
    private Integer bidderScore;

    @Getter
    private Integer otherScore;
}
