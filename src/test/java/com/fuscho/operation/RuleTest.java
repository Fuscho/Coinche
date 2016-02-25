package com.fuscho.operation;


import com.fuscho.model.card.Card;
import com.fuscho.model.card.SuitCard;
import com.fuscho.model.card.ValueCard;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class RuleTest {
    /**
     * Clubs : Trèfle
     * Diamonds : Carreau
     * Hearts : Coeur
     * Spades : Pique
     */


    /**
     *
     Quand on bat atout, le joueur qui possède dans son jeu au moins une carte d'atout plus forte que la plus forte carte d'atout déjà posée doit impérativement jouer l'une de ces cartes. On appelle cela "monter à l'atout". Cette règle s'applique même si c'est son partenaire qui a mis la carte d'atout la plus forte.
     De même, lorsqu'un adversaire a coupé la couleur d'entame, si le joueur qui ne peut fournir dans cette couleur détient au moins un atout plus fort que celui déjà joué, il est tenu de monter à l'atout. On dit qu'il "surcoupe".

     Si le joueur ne possède ni de cartes de la couleur d'entame, ni de cartes d'atout, il joue la carte de son choix.
     Si le joueur ne possède pas de cartes de la couleur d'entame et que la carte la plus forte a été déposée par son partenaire, il peut toutefois couper s'il le souhaite pour sauver un atout et/ou prendre la main (faire le pli et avoir l'initiative de l'entame suivante).
     Défausse[modifier | modifier le code]
     Les règles peuvent diverger parmi ces diverses possibilités.

     S'il ne lui est pas possible de surcouper, celui qui n'a pas la couleur peut :

     soit seulement jouer un atout plus faible (ce que l'on appelle également pisser à l'atout).
     soit il est autorisé en plus à se défausser ou faire un appel en jouant une autre couleur sans être obligé de pisser en atout tant que son partenaire est maître du pli.
     soit mettre une carte d'une autre couleur d'une forte valeur (un 10 par exemple) cela s'appelle une alcarette.
     il est impératif de monter à l'atout. S'il ne reste que de l'atout à un joueur et que son partenaire est maître après avoir coupé, son coéquipier a obligation de monter (si possible).
     * */

    //Si le joueur possède une carte de la couleur d'entame, il doit jouer la couleur
    @Test
    public void playerHasSuitAskedNotTrump(){

        SuitCard trumpSuit = SuitCard.Diamonds;
        SuitCard askedSuit = SuitCard.Clubs;
        Card masterCard = new Card(SuitCard.Clubs,ValueCard.Ten);
        Boolean partnerIsMaster = false;
        List<Card> playerHand = new ArrayList<Card>();
        Card valetTrefle = new Card(SuitCard.Clubs,ValueCard.Jack);
        Card dameTrefle = new Card(SuitCard.Clubs,ValueCard.Queen);
        Card asCarreau = new Card(SuitCard.Diamonds,ValueCard.Ace);
        Card dixCarreau = new Card(SuitCard.Diamonds,ValueCard.Ten);

        playerHand.add(valetTrefle);
        playerHand.add(dameTrefle);
        playerHand.add(asCarreau);
        playerHand.add(dixCarreau);

        List<Card> possibleMoves = Rule.getPossibleMoves(playerHand, askedSuit, trumpSuit, masterCard, partnerIsMaster);
        Assert.assertTrue(possibleMoves.size()==2);
        Assert.assertTrue(possibleMoves.contains(valetTrefle));
        Assert.assertTrue(possibleMoves.contains(dameTrefle));
    }

    //Si le joueur ne possède pas de cartes de la couleur d'entame et que partenaire maitre il joue la carte de son choix.
    @Test
    public void playerHasntSuitAskedNotTrumpPartnerIsMaster(){

        SuitCard trumpSuit = SuitCard.Diamonds;
        SuitCard askedSuit = SuitCard.Clubs;
        Card masterCard = new Card(SuitCard.Clubs,ValueCard.Ten);
        Boolean partnerIsMaster = true;
        List<Card> playerHand = new ArrayList<Card>();
        Card valetCoeur = new Card(SuitCard.Hearts,ValueCard.Jack);
        Card dameCoeur = new Card(SuitCard.Hearts,ValueCard.Queen);
        Card asCarreau = new Card(SuitCard.Diamonds,ValueCard.Ace);
        Card dixCarreau = new Card(SuitCard.Diamonds,ValueCard.Ten);

        playerHand.add(valetCoeur);
        playerHand.add(dameCoeur);
        playerHand.add(asCarreau);
        playerHand.add(dixCarreau);

        List<Card> possibleMoves = Rule.getPossibleMoves(playerHand, askedSuit, trumpSuit, masterCard, partnerIsMaster);
        Assert.assertTrue(possibleMoves.size()==4);
        Assert.assertTrue(possibleMoves.contains(dameCoeur));
        Assert.assertTrue(possibleMoves.contains(valetCoeur));
        Assert.assertTrue(possibleMoves.contains(asCarreau));
        Assert.assertTrue(possibleMoves.contains(dixCarreau));
    }

    // Si le joueur ne possède pas de cartes de la couleur d'entame,
    // partnaire pas maitre (carte maitre pas atout)
    // possède des cartes d'atout, il est obligé de jouer l'une de ses cartes d'atout.
    @Test
    public void playerHasntSuitAskedNotTrumpPartnerIsntMasterCut(){

        SuitCard trumpSuit = SuitCard.Diamonds;
        SuitCard askedSuit = SuitCard.Clubs;
        Card masterCard = new Card(SuitCard.Clubs,ValueCard.Ten);
        Boolean partnerIsMaster = false;
        List<Card> playerHand = new ArrayList<Card>();
        Card valetCoeur = new Card(SuitCard.Hearts,ValueCard.Jack);
        Card dameCoeur = new Card(SuitCard.Hearts,ValueCard.Queen);
        Card asCarreau = new Card(SuitCard.Diamonds,ValueCard.Ace);
        Card dixCarreau = new Card(SuitCard.Diamonds,ValueCard.Ten);

        playerHand.add(valetCoeur);
        playerHand.add(dameCoeur);
        playerHand.add(asCarreau);
        playerHand.add(dixCarreau);

        List<Card> possibleMoves = Rule.getPossibleMoves(playerHand, askedSuit, trumpSuit, masterCard, partnerIsMaster);
        Assert.assertTrue(possibleMoves.size()==2);
        Assert.assertTrue(possibleMoves.contains(asCarreau));
        Assert.assertTrue(possibleMoves.contains(dixCarreau));
    }

    // Si le joueur ne possède pas de cartes de la couleur d'entame,
    // partenaire pas maitre (carte maitre pas atout)
    // possède des cartes d'atout,
    // et que l'adversaire à déjà couper
    // il est obligé de jouer l'une de ses cartes d'atout au dessus.
    @Test
    public void playerHasntSuitAskedNotTrumpPartnerIsntMasterCutHigher(){

        SuitCard trumpSuit = SuitCard.Diamonds;
        SuitCard askedSuit = SuitCard.Clubs;
        Card masterCard = new Card(SuitCard.Diamonds,ValueCard.Ten);
        Boolean partnerIsMaster = false;
        List<Card> playerHand = new ArrayList<Card>();
        Card valetCoeur = new Card(SuitCard.Hearts,ValueCard.Jack);
        Card dameCoeur = new Card(SuitCard.Hearts,ValueCard.Queen);
        Card asCarreau = new Card(SuitCard.Diamonds,ValueCard.Ace);
        Card huitCarreau = new Card(SuitCard.Diamonds,ValueCard.Eight);

        playerHand.add(valetCoeur);
        playerHand.add(dameCoeur);
        playerHand.add(asCarreau);
        playerHand.add(huitCarreau);

        List<Card> possibleMoves = Rule.getPossibleMoves(playerHand, askedSuit, trumpSuit, masterCard, partnerIsMaster);
        Assert.assertTrue(possibleMoves.size()==1);
        Assert.assertTrue(possibleMoves.contains(asCarreau));
    }

    // Si le joueur ne possède pas de cartes de la couleur d'entame,
    // partenaire pas maitre (carte maitre pas atout)
    // possède des cartes d'atout,
    // et que l'adversaire à déjà couper
    // mais le joueur n'a pas une carte au dessus
    // joue ce qu'il veut.
    @Test
    public void playerHasntSuitAskedNotTrumpPartnerIsntMasterCutLower(){

        SuitCard trumpSuit = SuitCard.Diamonds;
        SuitCard askedSuit = SuitCard.Clubs;
        Card masterCard = new Card(SuitCard.Diamonds,ValueCard.Jack);
        Boolean partnerIsMaster = false;
        List<Card> playerHand = new ArrayList<Card>();
        Card valetCoeur = new Card(SuitCard.Hearts,ValueCard.Jack);
        Card dameCoeur = new Card(SuitCard.Hearts,ValueCard.Queen);
        Card asCarreau = new Card(SuitCard.Diamonds,ValueCard.Ace);
        Card huitCarreau = new Card(SuitCard.Diamonds,ValueCard.Eight);

        playerHand.add(valetCoeur);
        playerHand.add(dameCoeur);
        playerHand.add(asCarreau);
        playerHand.add(huitCarreau);

        List<Card> possibleMoves = Rule.getPossibleMoves(playerHand, askedSuit, trumpSuit, masterCard, partnerIsMaster);
        Assert.assertTrue(possibleMoves.size()==4);
        Assert.assertTrue(possibleMoves.contains(valetCoeur));
        Assert.assertTrue(possibleMoves.contains(dameCoeur));
        Assert.assertTrue(possibleMoves.contains(asCarreau));
        Assert.assertTrue(possibleMoves.contains(huitCarreau));
    }



}
