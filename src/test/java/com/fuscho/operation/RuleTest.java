package com.fuscho.operation;

import com.fuscho.model.card.Card;
import com.fuscho.model.card.SuitCard;
import com.fuscho.model.card.ValueCard;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jbfuss on 25/02/2016.
 */
public class RuleTest {

    @Test
    public void AceHeartShouldWinWithClubsTrump(){
        List<Card> cardsPlay = new ArrayList<>();
        cardsPlay.add(new Card(SuitCard.Hearts, ValueCard.Ace));
        cardsPlay.add(new Card(SuitCard.Hearts, ValueCard.King));
        cardsPlay.add(new Card(SuitCard.Hearts, ValueCard.Seven));
        cardsPlay.add(new Card(SuitCard.Diamonds, ValueCard.Jack));

        Card masterCard = Rule.getMasterCard(cardsPlay, SuitCard.Clubs);
        Assert.assertTrue(masterCard.equals(new Card(SuitCard.Hearts, ValueCard.Ace)));
    }

    @Test
    public void KingHeartShouldWinWithClubsTrump(){
        List<Card> cardsPlay = new ArrayList<>();
        cardsPlay.add(new Card(SuitCard.Hearts, ValueCard.King));
        cardsPlay.add(new Card(SuitCard.Diamonds, ValueCard.Ace));
        cardsPlay.add(new Card(SuitCard.Hearts, ValueCard.Seven));
        cardsPlay.add(new Card(SuitCard.Diamonds, ValueCard.Jack));

        Card masterCard = Rule.getMasterCard(cardsPlay, SuitCard.Clubs);
        Assert.assertTrue(masterCard.equals(new Card(SuitCard.Hearts, ValueCard.King)));
    }

    @Test
    public void KingTrumpShouldWinWithClubsTrump(){
        List<Card> cardsPlay = new ArrayList<>();
        cardsPlay.add(new Card(SuitCard.Hearts, ValueCard.Seven));
        cardsPlay.add(new Card(SuitCard.Diamonds, ValueCard.Ace));
        cardsPlay.add(new Card(SuitCard.Hearts, ValueCard.King));
        cardsPlay.add(new Card(SuitCard.Clubs, ValueCard.King));

        Card masterCard = Rule.getMasterCard(cardsPlay, SuitCard.Clubs);
        Assert.assertTrue(masterCard.equals(new Card(SuitCard.Clubs, ValueCard.King)));
    }

    @Test
    public void JackTrumpShouldWinWithClubsTrump(){
        List<Card> cardsPlay = new ArrayList<>();
        cardsPlay.add(new Card(SuitCard.Hearts, ValueCard.Seven));
        cardsPlay.add(new Card(SuitCard.Clubs, ValueCard.Jack));
        cardsPlay.add(new Card(SuitCard.Hearts, ValueCard.King));
        cardsPlay.add(new Card(SuitCard.Clubs, ValueCard.King));

        Card masterCard = Rule.getMasterCard(cardsPlay, SuitCard.Clubs);
        Assert.assertTrue(masterCard.equals(new Card(SuitCard.Clubs, ValueCard.Jack)));
    }

    @Test
    public void NineTrumpShouldWinWithHeartTrump(){
        List<Card> cardsPlay = new ArrayList<>();
        cardsPlay.add(new Card(SuitCard.Hearts, ValueCard.Seven));
        cardsPlay.add(new Card(SuitCard.Hearts, ValueCard.Ace));
        cardsPlay.add(new Card(SuitCard.Hearts, ValueCard.King));
        cardsPlay.add(new Card(SuitCard.Hearts, ValueCard.Nine));

        Card masterCard = Rule.getMasterCard(cardsPlay, SuitCard.Hearts);
        Assert.assertTrue(masterCard.equals(new Card(SuitCard.Hearts, ValueCard.Nine)));
    }
}
