package com.fuscho.model.card;

import org.junit.Assert;
import org.junit.Test;

/**
 * Créer par mchoraine le 25/02/2016.
 */
public class CardTest {

    @Test
    public void cardShouldBeHighterWithoutTrump(){
        Card cardLow = new Card(SuitCard.Hearts, ValueCard.Jack);
        Card cardHight = new Card(SuitCard.Hearts, ValueCard.Ten);
        SuitCard trump = SuitCard.Clubs;
        Assert.assertTrue(cardHight.getCardOrder(trump) > cardLow.getCardOrder(trump));
    }

    @Test
    public void cardShouldBeHighterWithoutTrump2(){
        Card cardLow = new Card(SuitCard.Hearts, ValueCard.Nine);
        Card cardHight = new Card(SuitCard.Hearts, ValueCard.Ten);
        SuitCard trump = SuitCard.Clubs;
        Assert.assertTrue(cardHight.getCardOrder(trump) > cardLow.getCardOrder(trump));
    }

    @Test
    public void cardShouldBeHighterTrump(){
        Card cardHight = new Card(SuitCard.Hearts, ValueCard.Jack);
        Card cardLow = new Card(SuitCard.Hearts, ValueCard.Ten);
        SuitCard trump = SuitCard.Hearts;
        Assert.assertTrue(cardHight.getCardOrder(trump) > cardLow.getCardOrder(trump));
    }

    @Test
    public void cardShouldBeHighterTrump2(){
        Card cardHight = new Card(SuitCard.Hearts, ValueCard.Nine);
        Card cardLow = new Card(SuitCard.Hearts, ValueCard.Ten);
        SuitCard trump = SuitCard.Hearts;
        Assert.assertTrue(cardHight.getCardOrder(trump) > cardLow.getCardOrder(trump));
    }
}
