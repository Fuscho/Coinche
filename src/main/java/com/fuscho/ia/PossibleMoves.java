package com.fuscho.ia;

import com.fuscho.model.card.Card;
import com.fuscho.model.card.factory.CardFactory;
import com.fuscho.model.player.IAPlayer;
import com.fuscho.model.player.OtherPlayer;
import com.fuscho.model.player.Player;
import com.fuscho.model.player.TeamManager;
import com.sun.org.apache.xpath.internal.operations.Bool;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by jbfuss on 02/03/2016.
 *
 * Class to calculate possibleMoves
 */
public class PossibleMoves {
    /**
     * init possible moves
     * Init other players possible moves in relation with the player hand
     * @param cardsPlayer
     */
    public static HashMap<Card,Double> initOtherPlayerPossibleMoves(List<Card> cardsPlayer){
        //Create card factory
        List<Card> cards = CardFactory.createCardPack();

        HashMap<Card,Double> possibleMoves = new HashMap<>();
        //For each card of the factory
        for(Card cardOtherPlayer: cards){
            Double ponderation = 0.5;

            List<Card> playerCard = cardsPlayer.stream().filter(
                    cardPlayer -> cardPlayer.getSuit() == cardOtherPlayer.getSuit()
                            && cardPlayer.getValue() == cardOtherPlayer.getValue()
            ).collect(Collectors.toList());
            if(playerCard.size()>0){
                ponderation = 0.0;
            }
            possibleMoves.put(cardOtherPlayer,ponderation);
        }

        return possibleMoves;
    }


    //TODO UPDATE WHEN CARD IS PLAY (PARAMS cardPlay, GAME)
    // => foreach otherPlayer (IA) update possible move of player play card
    //CUT PISS ...
}
