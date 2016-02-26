package com.fuscho.model.player;

import com.fuscho.model.card.Card;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * Créer par mchoraine le 25/02/2016.
 */
@Data
@NoArgsConstructor
public abstract class Player {
    private List<Card> cards;
    private List<Card> cardsWin;
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

    @Override
    public String toString(){
        return "fdp";
    }
}
