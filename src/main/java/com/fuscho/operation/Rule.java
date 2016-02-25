package com.fuscho.operation;

import com.fuscho.model.card.Card;
import com.fuscho.model.card.SuitCard;

import java.util.List;
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
            if (askedSuit == trumpSuit) {
                //Player has stronger card than master card
                List<Card> possibleMovesHigher = possibleMoves.
                        stream()
                        .filter(card -> card.getCardOrder(trumpSuit) > masterCard.getCardOrder(trumpSuit))
                        .collect(Collectors.toList());

                if (possibleMovesHigher.size() > 0) {
                    possibleMoves = possibleMovesHigher;
                }
                //Player hasn't stronger card than asked => DO NOTHING (possibleMoves doesn't change)

            }
            //asked suit isn't trump suit => DO NOTHING (possibleMoves doesn't change)

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
                }
            }
        }

        return possibleMoves;
    }
}
