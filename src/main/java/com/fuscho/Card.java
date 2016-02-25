package com.fuscho;

import lombok.Data;

/**
 * Créer par mchoraine le 25/02/2016.
 */
@Data
public class Card {
    private SuitCard suit;
    private ValueCard value;

    public Card(SuitCard suit, ValueCard value){
        this.suit = suit;
        this.value = value;
    }
}



