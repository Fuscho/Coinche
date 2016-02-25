package com.fuscho.operation;

import com.fuscho.model.card.Card;
import com.fuscho.model.card.SuitCard;

import java.util.List;
import java.util.stream.Collectors;

public static class Rule {

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
            -> non tu joues ce que tu veux
     */

    public static List<Card> getPossibleMoves(List<Card> playerHand, SuitCard askedSuit, SuitCard trumpSuit, Card masterCard, Boolean partnerIsMaster){

        List<Card> possibleMoves = playerHand.stream().filter(card -> card.getSuit() == askedSuit).collect(Collectors.toList());

        //Player has the asked suit
        if(possibleMoves > O){
            //asked suit is trump suit
            if(askedSuit==trumpSuit){
                //Player has stronger card than asked
               /* possibleMoves = possibleMoves.
                        stream()
                        .filter(card -> card.getValue() > masterCard.)
                        .collect(Collectors.toList());*/

            }
            //asked suit isn't trump suit => DO NOTHING JUST RETURN possibleMoves




        }else{

        }



    }
}
