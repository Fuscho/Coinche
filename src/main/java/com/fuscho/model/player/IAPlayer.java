package com.fuscho.model.player;

import com.fuscho.model.card.Card;
import com.fuscho.model.card.factory.CardFactory;
import com.fuscho.model.game.TurnRound;
import com.fuscho.operation.Rule;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Créer par mchoraine le 26/02/2016.
 */
public class IAPlayer extends Player{
    public List<OtherPlayer> otherPlayers;

    public IAPlayer(String name) {
        this.setName(name);
    }

    @Override
    public Card getRandomCard(TurnRound currentTurn) {
        List<Card> possibleMoves = Rule.getPossibleMoves(this.getCards(), currentTurn.getSuitAsked(), currentTurn.getTrumpSuit(), currentTurn.getMasterCard(), currentTurn.isPartenaireMaster(this));
        return possibleMoves.get(0);
    }

    public void initPossibleMovesOtherPlayers(){
        List<Card> cards = CardFactory.createCardPack();
        HashMap<Card,Double> possibleMoves = new HashMap<>();
        for(Card cardOtherPlayer: cards){
            Double ponderation = 0.5;
            List<Card> playerCard = this.getCards().stream().filter(
                    cardPlayer -> cardPlayer.getSuit() == cardOtherPlayer.getSuit()
                            && cardPlayer.getValue() == cardOtherPlayer.getValue()
            ).collect(Collectors.toList());
            if(playerCard.size()>0){
                ponderation = 0.0;
            }
            possibleMoves.put(cardOtherPlayer,ponderation);
        }

        OtherPlayer adversaire1 = new OtherPlayer();
        adversaire1.setPossibleMoves(possibleMoves);

        OtherPlayer partner = new OtherPlayer();
        partner.setPossibleMoves(possibleMoves);

        OtherPlayer adversaire2 = new OtherPlayer();
        adversaire1.setPossibleMoves(possibleMoves);

        this.otherPlayers.add(adversaire1);
        this.otherPlayers.add(partner);
        this.otherPlayers.add(adversaire2);

    }
}
