package com.fuscho.model.card;

import com.fuscho.model.card.factory.CardFactory;

import java.util.Collections;
import java.util.List;

/**
 * Créer par mchoraine le 25/02/2016.
 */
public class CardPack {
    private List<Card> cards = CardFactory.createCardPack();

    public void shuffleCards(){
        Collections.shuffle(cards);
    }

    public void cutCards(){

    }

    public List<Card> dealThreeCards() {
        return cards.subList(0, 2);
    }

    public List<Card> dealTwoCards() {
        return cards.subList(0, 1);
    }
}
