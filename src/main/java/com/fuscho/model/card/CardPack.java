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
        Collections.shuffle(cards);
        Collections.shuffle(cards);
    }

    public void cutCards(){

    }

    public List<Card> dealThreeCards() {
        List<Card> cardsToGive = this.cards.subList(0, 3);
        this.cards = this.cards.subList(3, this.cards.size());
        return cardsToGive;
    }

    public List<Card> dealTwoCards() {
        List<Card> cardsToGive = this.cards.subList(0, 2);
        this.cards = this.cards.subList(2, this.cards.size());
        return cardsToGive;
    }
}
