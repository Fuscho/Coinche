package com.fuscho.ia;

import com.fuscho.model.card.Card;
import com.fuscho.model.card.factory.CardFactory;
import com.fuscho.model.game.Game;
import com.fuscho.model.game.TurnRound;
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

    /**
     * UpdatePossibleMove
     * Update possible move of otherPlayer after player play Card
     * @param turnRound
     * @param playerPlayCard
     * @param iaPlayer
     * @param cardPlay
     * @param otherPlayer
     */
    public static void updatePossibleMove(TurnRound turnRound, Player playerPlayCard,Player iaPlayer, Card cardPlay, OtherPlayer otherPlayer){
        //Update ponderation of possible move of the card play to 0
        updatePonderation(otherPlayer.possibleMoves,0.0,cardPlay);
        List<Card> cardPack = CardFactory.createCardPack();
        if(playerPlayCard==otherPlayer.getPlayer()){
            //OTHERPLAYER IS PLayerPlayCard

            //Card play is suit asked
            if(cardPlay.getSuit()==turnRound.getSuitAsked()){
                //Suit asked is trump suit
                if(turnRound.getSuitAsked()==turnRound.getTrumpSuit()){
                    //Card play is less than masterCard
                    if(cardPlay.getCardOrder(turnRound.getTrumpSuit())<turnRound.getMasterCard().getCardOrder(turnRound.getTrumpSuit())){
                        //Update ponderation to 0 for all card > cardPlay
                        List<Card> cardPackFiltered = cardPack.stream().filter(
                            card -> card.getSuit()==turnRound.getTrumpSuit()
                            &&
                            card.getCardOrder(turnRound.getTrumpSuit())>cardPlay.getCardOrder(turnRound.getTrumpSuit())
                        ).collect(Collectors.toList());

                        for(Card card : cardPackFiltered){
                            updatePonderation(otherPlayer.getPossibleMoves(),0.0,cardPlay);
                        }
                    }
                }
            }else{
                //Update ponderation of possible move of the cards suit to 0
                List<Card> cardPackFiltered = cardPack.stream().filter(
                        card -> card.getSuit()==turnRound.getSuitAsked()
                ).collect(Collectors.toList());

                for(Card card : cardPackFiltered) {
                    updatePonderation(otherPlayer.getPossibleMoves(), 0.0, cardPlay);
                }
                //SUit asked isn't trump
                if(turnRound.getSuitAsked()!= turnRound.getTrumpSuit()){
                    //Partner not master and player doesn't cut
                    if(!turnRound.isPartenaireMaster(iaPlayer) && cardPlay.getSuit()!=turnRound.getTrumpSuit()){
                        //masterCard isn't trump suit => player doesn't have trump
                        if(turnRound.getMasterCard().getSuit()!=turnRound.getTrumpSuit()){
                            //Update ponderation of possible move of trump cards to 0
                            cardPackFiltered = cardPack.stream().filter(
                                    card -> card.getSuit()==turnRound.getTrumpSuit()
                            ).collect(Collectors.toList());

                            for(Card card : cardPackFiltered){
                                updatePonderation(otherPlayer.getPossibleMoves(),0.0,cardPlay);
                            }
                        }else{
                            //Player can't over cut
                            //Update ponderation to 0 for all card > cardPlay
                            cardPackFiltered = cardPack.stream().filter(
                                    card -> card.getSuit()==turnRound.getTrumpSuit()
                                            &&
                                            card.getCardOrder(turnRound.getTrumpSuit())>cardPlay.getCardOrder(turnRound.getTrumpSuit())
                            ).collect(Collectors.toList());

                            for(Card card : cardPackFiltered){
                                updatePonderation(otherPlayer.getPossibleMoves(),0.0,cardPlay);
                            }
                        }

                    }
                }
            }
        }

    }



    public static void updatePonderation(HashMap<Card,Double> possibleMoves,Double ponderation, Card card) {
        possibleMoves.put(card, ponderation);
    }

//TODO update PONDERATION when all player except one have 0 on ponderation => for this player ponderation == 1
}
