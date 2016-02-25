package com.fuscho;

import com.fuscho.model.card.Card;
import com.fuscho.model.card.CardFactory;
import com.fuscho.model.card.SuitCard;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Créer par mchoraine le 25/02/2016.
 */
public class CardFactoryTest {

    @Test
    public void shouldCreate32Cards(){
        List<Card> cardPack = CardFactory.createCardPack();
        Assert.assertEquals(cardPack.size(), 32);
    }

    @Test
    public void shouldCreate4SuitCards(){
        List<Card> cardPack = CardFactory.createCardPack();
        List<Card> clubsCards = cardPack.stream().filter(card -> card.getSuit().equals(SuitCard.Clubs)).collect(Collectors.toList());
        List<Card> diamondsCards = cardPack.stream().filter(card -> card.getSuit().equals(SuitCard.Diamonds)).collect(Collectors.toList());
        List<Card> heartsCards = cardPack.stream().filter(card -> card.getSuit().equals(SuitCard.Hearts)).collect(Collectors.toList());
        List<Card> spadesCards = cardPack.stream().filter(card -> card.getSuit().equals(SuitCard.Spades)).collect(Collectors.toList());
        Assert.assertEquals(clubsCards.size(), 8);
        Assert.assertEquals(diamondsCards.size(), 8);
        Assert.assertEquals(heartsCards.size(), 8);
        Assert.assertEquals(spadesCards.size(), 8);
    }
}
