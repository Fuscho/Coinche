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

    public Integer getCardOrder(Card card, SuitCard trumpSuit){
        if(card.getSuit().equals(trumpSuit)){
            return this.value.trumpOrder;
        } else {
            return this.value.withoutTrumpOrder;
        }
    }

    public Integer getCardValueScore(Card card, SuitCard trumpSuit){
        if(card.getSuit().equals(trumpSuit)){
            return this.value.trumpValue;
        } else {
            return this.value.withoutTrumpValue;
        }
    }
}



