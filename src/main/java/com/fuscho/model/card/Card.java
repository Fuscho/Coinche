package com.fuscho.model.card;

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

    public Integer getCardOrder(SuitCard trumpSuit){
        if(this.suit.equals(trumpSuit)){
            return this.value.trumpOrder;
        } else {
            return this.value.withoutTrumpOrder;
        }
    }

    public Integer getCardValueScore(SuitCard trumpSuit){
        if(this.suit.equals(trumpSuit)){
            return this.value.trumpValue;
        } else {
            return this.value.withoutTrumpValue;
        }
    }

    public Integer getPoint(SuitCard trumpSuit) {
        if(trumpSuit.equals(this.getSuit())){
            return value.trumpValue;
        } else {
            return value.withoutTrumpValue;
        }
    }
}



