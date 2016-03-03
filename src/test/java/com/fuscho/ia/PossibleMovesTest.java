package com.fuscho.ia;

import com.fuscho.model.card.Card;
import com.fuscho.model.card.SuitCard;
import com.fuscho.model.card.ValueCard;
import com.fuscho.model.card.factory.CardFactory;
import com.fuscho.model.player.*;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jbfuss on 02/03/2016.
 */
public class PossibleMovesTest {
    @Test
    public void initPossibleMove(){
        IAPlayer player = new IAPlayer("IA1");
        IAPlayer p2 = new IAPlayer("IA1");
        IAPlayer p3 = new IAPlayer("IA1");
        IAPlayer p4 = new IAPlayer("IA1");

        List<Card> cardsPlayer = new ArrayList<>();

        cardsPlayer.add(new Card(SuitCard.Clubs,ValueCard.Ace));
        cardsPlayer.add(new Card(SuitCard.Clubs,ValueCard.Ten));
        cardsPlayer.add(new Card(SuitCard.Clubs,ValueCard.Eight));
        cardsPlayer.add(new Card(SuitCard.Diamonds,ValueCard.Queen));
        cardsPlayer.add(new Card(SuitCard.Diamonds,ValueCard.Nine));
        cardsPlayer.add(new Card(SuitCard.Diamonds,ValueCard.Seven));
        cardsPlayer.add(new Card(SuitCard.Spades,ValueCard.Queen));
        cardsPlayer.add(new Card(SuitCard.Spades,ValueCard.Jack));

        player.setCards(cardsPlayer);

        List<Player> otherPlayers = new ArrayList<>();
        otherPlayers.add(p2);
        otherPlayers.add(p3);
        otherPlayers.add(p4);

        player.initPossibleMoves(otherPlayers);

        for(OtherPlayer otherPlayer : player.getOtherPlayers()){
            for (Card card : CardFactory.createCardPack()) {
                if (cardsPlayer.contains(card)) {
                    Assert.assertEquals(otherPlayer.getPossibleMoves().get(card), 0.0, 0.0);
                }else{
                    Assert.assertEquals(otherPlayer.getPossibleMoves().get(card), 0.5, 0.0);
                }
            }
        }

    }

}
