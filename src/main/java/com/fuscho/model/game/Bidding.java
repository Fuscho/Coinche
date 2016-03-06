package com.fuscho.model.game;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Créer par mchoraine le 06/03/2016.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Bidding {
    private String suit;
    private Integer value;
}
