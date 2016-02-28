package com.fuscho.model.player;

import com.fuscho.model.card.Card;
import com.fuscho.model.game.TurnRound;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Créer par mchoraine le 25/02/2016.
 */
@Data
@NoArgsConstructor
public abstract class Player {

    private String name;
    private List<Card> cards;
    private List<Card> cardsWin = new ArrayList<>();
    private Player partner;

    public void addCards(List<Card> cardsReceive) {
        if(cards==null){
            this.cards = new ArrayList<>();
        }
        this.cards.addAll(cardsReceive);
    }

    public void playThisCard(Card card) {
        cards.remove(card);
    }

    public void addCardsWin(List<Card> cardsReceive) {
        if(cardsWin==null){
            this.cardsWin = new ArrayList<>();
        }
        this.cardsWin.addAll(cardsReceive);
    }

    public List<Card> getCards(){
        Collections.sort(cards, (card1, card2) -> card1.getCardName().compareTo(card2.getCardName()));
        return cards;
    }

    public abstract Card getRandomCard(TurnRound currentTurn);

    @Override
    public String toString(){
        return this.name;
    }
}
