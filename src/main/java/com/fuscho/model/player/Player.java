package com.fuscho.model.player;

import com.fuscho.model.card.Card;
import com.fuscho.model.game.TurnRound;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.builder.CompareToBuilder;

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
        Collections.sort(cards, (card1, card2) -> new CompareToBuilder().append(card1.getSuit(), card2.getSuit()).append(card1.getValue().withoutTrumpOrder, card2.getValue().withoutTrumpOrder).toComparison());
        return cards;
    }

    public abstract Card getRandomCard(TurnRound currentTurn);

    @Override
    public String toString(){
        return this.name;
    }
}
