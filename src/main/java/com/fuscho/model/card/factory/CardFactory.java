package com.fuscho.model.card.factory;

import com.fuscho.model.card.Card;
import com.fuscho.model.card.SuitCard;
import com.fuscho.model.card.ValueCard;

import java.util.ArrayList;
import java.util.List;

/**
 * Créer par mchoraine le 25/02/2016.
 */
public class CardFactory {

    public static List<Card> createCardPack(){
        List<Card> cards = new ArrayList<>();
        for(SuitCard suit : SuitCard.values()){
            for(ValueCard value : ValueCard.values()){
                cards.add(new Card(suit, value));
            }
        }
        return cards;
    }
}
