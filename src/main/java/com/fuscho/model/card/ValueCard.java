package com.fuscho.model.card;

/**
 * Créer par mchoraine le 25/02/2016.
 */
public enum ValueCard {
    Ace(new Integer[]{11, 5}, new Integer[]{11, 7}),
    King(new Integer[]{4, 3}, new Integer[]{4, 5}),
    Queen(new Integer[]{3, 2}, new Integer[]{3, 4}),
    Jack(new Integer[]{20, 7}, new Integer[]{2, 3}),
    Ten(new Integer[]{10, 4}, new Integer[]{10, 7}),
    Nine(new Integer[]{14, 6}, new Integer[]{0, 2}),
    Eight(new Integer[]{0, 1}, new Integer[]{0, 1}),
    Seven(new Integer[]{0, 0}, new Integer[]{0, 0});

    public final Integer trumpValue;
    public final Integer withoutTrumpValue;
    public final Integer trumpOrder;
    public final Integer withoutTrumpOrder;

    /**
     * @param trump : Value and order of the card trump
     * @param withoutTrump : Value and order of the card without trump
     */
    ValueCard(Integer[] trump, Integer[] withoutTrump){
        this.trumpValue = trump[0];
        this.trumpOrder = trump[1];
        this.withoutTrumpValue = withoutTrump[0];
        this.withoutTrumpOrder = withoutTrump[1];
    }
}