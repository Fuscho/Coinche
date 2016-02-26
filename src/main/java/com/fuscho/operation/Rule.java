package com.fuscho.operation;

import com.fuscho.model.card.Card;
import com.fuscho.model.card.SuitCard;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Rule {

    /**
        cartes du joueur
        couleur demandée
        couleur atout
        carte maitre
        partenaire maitre (BOOL)


        tu as la couleur demandée
            -> oui -> la couleur demandée est de l’atout
                ->oui tu as une carte au dessus
                    ->oui tu joues une carte au dessus (ATOUT)
                    -> non tu joues en dessous (ATOUT)
                -> non tu joues la couleur demandée (la carte que tu veux)
            -> non ->  la couleur demandée est de l’atout
                -> oui tu joues ce que tu veux
                ->non ton partenaire est maitre
                    -> oui tu joues ce que tu veux
                    ->non tu as de l’atout
                        -> oui tu joues de l’atout
                            -> si carte maitre est de l'atout
                                -> oui si tu as au dessus
                                    -> oui tu pose un atout au dessus
                                    -> non tu joues ce que tu veux
                                -> non tu joues ce que tu veux
                        -> non tu joues ce que tu veux
     */

    public static List<Card> getPossibleMoves(List<Card> playerHand, SuitCard askedSuit, SuitCard trumpSuit, Card masterCard, Boolean partnerIsMaster) {

        List<Card> possibleMoves = playerHand.stream().filter(card -> card.getSuit() == askedSuit).collect(Collectors.toList());

        //Player has the asked suit
        if (possibleMoves.size() > 0) {
            //asked suit is trump suit
            possibleMoves = getPossibleMovesTrump(possibleMoves, possibleMoves, trumpSuit, masterCard);

        } else {
            //Player hasn't the asked suit
            //asked suit is trump suit
            if (askedSuit == trumpSuit) {
                //Player can play all cards
                possibleMoves = playerHand;
            }else{
                //Player's partner is master
                if(partnerIsMaster){
                    //Player can play all cards
                    possibleMoves = playerHand;
                }else{
                    possibleMoves = getPossibleMovesTrump(playerHand, possibleMoves, trumpSuit, masterCard);
                }
            }
        }

        return possibleMoves;
    }

    public static List<Card> getPossibleMovesTrump(List<Card> playerHand, List<Card> possibleMoves, SuitCard trumpSuit, Card masterCard){

        if(masterCard.getSuit() == trumpSuit){

            //Player has stronger card than masterCard
            List<Card> possibleMovesHigher = possibleMoves.
                    stream()
                    .filter(card -> card.getCardOrder(trumpSuit) > masterCard.getCardOrder(trumpSuit))
                    .collect(Collectors.toList());

            if (possibleMovesHigher.size() > 0) {
                possibleMoves = possibleMovesHigher;
            }
            //Player hasn't stronger card than asked => Play what you want
            possibleMoves = playerHand;
        }else{
            //Play what you want
            possibleMoves = playerHand;
        }

        return possibleMoves;

    }

    /**
     * Si y a de l'atout sur la table -> la plus forte à l'atout
     * Sinon la plus forte normal
     * @param cardsOnTable
     * @param trumpSuit
     * @return
     */
    public static Card getMasterCard(List<Card> cardsOnTable, SuitCard trumpSuit) {
        Optional<Card> first = cardsOnTable.stream().filter(c -> c.getSuit().equals(trumpSuit)).findFirst();
        if(first.isPresent()){
            Comparator<Card> comparator = Comparator.comparing(card -> card.getValue().trumpOrder);
            List<Card> trumpCards = cardsOnTable.stream().filter(card -> card.getSuit().equals(trumpSuit)).collect(Collectors.toList());
            Collections.sort(trumpCards, comparator.reversed());
            return trumpCards.get(0);
        } else {
            Comparator<Card> comparator = Comparator.comparing(card -> card.getValue().withoutTrumpOrder);
            List<Card> askedCards = cardsOnTable.stream().filter(card -> card.getSuit().equals(cardsOnTable.get(0).getSuit())).collect(Collectors.toList());
            Collections.sort(askedCards, comparator.reversed());
            return askedCards.get(0);
        }
    }
}
