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
public class Player {
    private List<Card> cards;
    private OtherPlayer adversary1;
    private OtherPlayer adversary2;
    private OtherPlayer partner;
    private Boolean human;

    public void addCards(List<Card> cardsReceive) {
        if(cards==null){
            this.cards = new ArrayList<>();
        }
        this.cards.addAll(cardsReceive);
    }

    public void playThisCard(Card card) {
        cards.remove(card);
    }
}
