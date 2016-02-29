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



    /** Jouer la carte demandée*/
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

    /** Pas obliger de couper */
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

    /** N'a pas la couleur demandée et pas d'atout */
    //Si le joueur ne possède pas de cartes de la couleur d'entame et pas d'atout il joue la carte de son choix.
    @Test
    public void playerHasntSuitAskedNotTrumpAndHasNoTrump(){

        SuitCard trumpSuit = SuitCard.Spades;
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
        Assert.assertTrue(possibleMoves.size()==4);
        Assert.assertTrue(possibleMoves.contains(dameCoeur));
        Assert.assertTrue(possibleMoves.contains(valetCoeur));
        Assert.assertTrue(possibleMoves.contains(asCarreau));
        Assert.assertTrue(possibleMoves.contains(dixCarreau));
    }

    /** Couper */
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

    /** Surcouper */
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

    /** Pisser après une coupe d'un adversaire */
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

    /** MONTER à l'atout même si son partenaire n'est pas maitre */
    //à l'atout, le joueur possède des cartes d'atout plus forte
    // doit impérativement jouer l'une de ces cartes.
    @Test
    public void playerHasSuitAskedTrumpPartnerIsntMasterHigher(){

        SuitCard trumpSuit = SuitCard.Clubs;
        SuitCard askedSuit = SuitCard.Clubs;
        Card masterCard = new Card(SuitCard.Clubs,ValueCard.Nine);
        Boolean partnerIsMaster = false;
        List<Card> playerHand = new ArrayList<Card>();
        Card valetTrefle = new Card(SuitCard.Clubs,ValueCard.Jack);
        Card dameTrefle = new Card(SuitCard.Clubs,ValueCard.Queen);
        Card asCarreau = new Card(SuitCard.Diamonds,ValueCard.Ace);
        Card huitCarreau = new Card(SuitCard.Diamonds,ValueCard.Eight);

        playerHand.add(valetTrefle);
        playerHand.add(dameTrefle);
        playerHand.add(asCarreau);
        playerHand.add(huitCarreau);

        List<Card> possibleMoves = Rule.getPossibleMoves(playerHand, askedSuit, trumpSuit, masterCard, partnerIsMaster);
        Assert.assertTrue(possibleMoves.size()==1);
        Assert.assertTrue(possibleMoves.contains(valetTrefle));
    }

    /** MONTER à l'atout même si son partenaire est maitre */
    //à l'atout, le joueur possède des cartes d'atout plus forte
    //que son advesaire est maitre
    // doit impérativement jouer l'une de ces cartes.
    @Test
    public void playerHasSuitAskedTrumpPartnerMasterHigher(){

        SuitCard trumpSuit = SuitCard.Clubs;
        SuitCard askedSuit = SuitCard.Clubs;
        Card masterCard = new Card(SuitCard.Clubs,ValueCard.Nine);
        Boolean partnerIsMaster = true;
        List<Card> playerHand = new ArrayList<Card>();
        Card valetTrefle = new Card(SuitCard.Clubs,ValueCard.Jack);
        Card dameTrefle = new Card(SuitCard.Clubs,ValueCard.Queen);
        Card asCarreau = new Card(SuitCard.Diamonds,ValueCard.Ace);
        Card huitCarreau = new Card(SuitCard.Diamonds,ValueCard.Eight);

        playerHand.add(valetTrefle);
        playerHand.add(dameTrefle);
        playerHand.add(asCarreau);
        playerHand.add(huitCarreau);

        List<Card> possibleMoves = Rule.getPossibleMoves(playerHand, askedSuit, trumpSuit, masterCard, partnerIsMaster);
        Assert.assertTrue(possibleMoves.size()==1);
        Assert.assertTrue(possibleMoves.contains(valetTrefle));
    }

    /** Jouer de l'atout même si on ne peut pas monter */
    //à l'atout, le joueur possède des cartes d'atout plus faible
    //que son advesaire n'est pas maitre
    // doit impérativement jouer l'une de ces cartes.
    @Test
    public void playerHasSuitAskedTrumpPartnerIsntMasterLower(){

        SuitCard trumpSuit = SuitCard.Clubs;
        SuitCard askedSuit = SuitCard.Clubs;
        Card masterCard = new Card(SuitCard.Clubs,ValueCard.Jack);
        Boolean partnerIsMaster = false;
        List<Card> playerHand = new ArrayList<Card>();
        Card neufTrefle = new Card(SuitCard.Clubs,ValueCard.Nine);
        Card dameTrefle = new Card(SuitCard.Clubs,ValueCard.Queen);
        Card asCarreau = new Card(SuitCard.Diamonds,ValueCard.Ace);
        Card huitCarreau = new Card(SuitCard.Diamonds,ValueCard.Eight);

        playerHand.add(neufTrefle);
        playerHand.add(dameTrefle);
        playerHand.add(asCarreau);
        playerHand.add(huitCarreau);

        List<Card> possibleMoves = Rule.getPossibleMoves(playerHand, askedSuit, trumpSuit, masterCard, partnerIsMaster);
        Assert.assertTrue(possibleMoves.size()==2);
        Assert.assertTrue(possibleMoves.contains(neufTrefle));
        Assert.assertTrue(possibleMoves.contains(dameTrefle));
    }

    /** Jouer ce que l'on veut lorsqu'on a pas d'atout */
    //à l'atout, le joueur ne possède pas de carte d'atout
    //que son advesaire n'est pas maitre
    // peut jouer ce qu'il veut
    @Test
    public void playerHasntSuitAskedTrumpPartnerIsntMaster(){

        SuitCard trumpSuit = SuitCard.Spades;
        SuitCard askedSuit = SuitCard.Spades;
        Card masterCard = new Card(SuitCard.Spades,ValueCard.Jack);
        Boolean partnerIsMaster = false;
        List<Card> playerHand = new ArrayList<Card>();
        Card neufTrefle = new Card(SuitCard.Clubs,ValueCard.Nine);
        Card dameTrefle = new Card(SuitCard.Clubs,ValueCard.Queen);
        Card asCarreau = new Card(SuitCard.Diamonds,ValueCard.Ace);
        Card huitCarreau = new Card(SuitCard.Diamonds,ValueCard.Eight);

        playerHand.add(neufTrefle);
        playerHand.add(dameTrefle);
        playerHand.add(asCarreau);
        playerHand.add(huitCarreau);

        List<Card> possibleMoves = Rule.getPossibleMoves(playerHand, askedSuit, trumpSuit, masterCard, partnerIsMaster);
        Assert.assertTrue(possibleMoves.size()==4);
        Assert.assertTrue(possibleMoves.contains(neufTrefle));
        Assert.assertTrue(possibleMoves.contains(dameTrefle));
        Assert.assertTrue(possibleMoves.contains(asCarreau));
        Assert.assertTrue(possibleMoves.contains(huitCarreau));
    }

    /** le joueur commence */
    @Test
    public void playerStart(){

        SuitCard trumpSuit = SuitCard.Spades;
        SuitCard askedSuit = null;
        Card masterCard = null;
        Boolean partnerIsMaster = false;
        List<Card> playerHand = new ArrayList<Card>();
        Card neufTrefle = new Card(SuitCard.Clubs,ValueCard.Nine);
        Card dameTrefle = new Card(SuitCard.Clubs,ValueCard.Queen);
        Card asCarreau = new Card(SuitCard.Diamonds,ValueCard.Ace);
        Card huitCarreau = new Card(SuitCard.Diamonds,ValueCard.Eight);

        playerHand.add(neufTrefle);
        playerHand.add(dameTrefle);
        playerHand.add(asCarreau);
        playerHand.add(huitCarreau);

        List<Card> possibleMoves = Rule.getPossibleMoves(playerHand, askedSuit, trumpSuit, masterCard, partnerIsMaster);
        Assert.assertTrue(possibleMoves.size()==4);
        Assert.assertTrue(possibleMoves.contains(neufTrefle));
        Assert.assertTrue(possibleMoves.contains(dameTrefle));
        Assert.assertTrue(possibleMoves.contains(asCarreau));
        Assert.assertTrue(possibleMoves.contains(huitCarreau));
    }



    @Test
    public void AceHeartShouldWinWithClubsTrump(){
        List<Card> cardsPlay = new ArrayList<>();
        cardsPlay.add(new Card(SuitCard.Hearts, ValueCard.Ace));
        cardsPlay.add(new Card(SuitCard.Hearts, ValueCard.King));
        cardsPlay.add(new Card(SuitCard.Hearts, ValueCard.Seven));
        cardsPlay.add(new Card(SuitCard.Diamonds, ValueCard.Jack));

        Card masterCard = Rule.getMasterCard(cardsPlay, SuitCard.Clubs);
        Assert.assertTrue(masterCard.equals(new Card(SuitCard.Hearts, ValueCard.Ace)));
    }

    @Test
    public void KingHeartShouldWinWithClubsTrump(){
        List<Card> cardsPlay = new ArrayList<>();
        cardsPlay.add(new Card(SuitCard.Hearts, ValueCard.King));
        cardsPlay.add(new Card(SuitCard.Diamonds, ValueCard.Ace));
        cardsPlay.add(new Card(SuitCard.Hearts, ValueCard.Seven));
        cardsPlay.add(new Card(SuitCard.Diamonds, ValueCard.Jack));

        Card masterCard = Rule.getMasterCard(cardsPlay, SuitCard.Clubs);
        Assert.assertTrue(masterCard.equals(new Card(SuitCard.Hearts, ValueCard.King)));
    }

    @Test
    public void AceSpadesShouldWinWithClubsTrump(){
        List<Card> cardsPlay = new ArrayList<>();
        cardsPlay.add(new Card(SuitCard.Spades, ValueCard.King));
        cardsPlay.add(new Card(SuitCard.Spades, ValueCard.Ace));
        cardsPlay.add(new Card(SuitCard.Spades, ValueCard.Ten));
        cardsPlay.add(new Card(SuitCard.Spades, ValueCard.Jack));

        Card masterCard = Rule.getMasterCard(cardsPlay, SuitCard.Clubs);
        Assert.assertTrue(masterCard.equals(new Card(SuitCard.Spades, ValueCard.Ace)));
    }

    @Test
    public void QueenHeartShouldWinWithClubsTrump(){
        List<Card> cardsPlay = new ArrayList<>();
        cardsPlay.add(new Card(SuitCard.Hearts, ValueCard.Queen));
        cardsPlay.add(new Card(SuitCard.Diamonds, ValueCard.Ace));
        cardsPlay.add(new Card(SuitCard.Spades, ValueCard.King));
        cardsPlay.add(new Card(SuitCard.Diamonds, ValueCard.Jack));

        Card masterCard = Rule.getMasterCard(cardsPlay, SuitCard.Clubs);
        Assert.assertTrue(masterCard.equals(new Card(SuitCard.Hearts, ValueCard.Queen)));
    }

    @Test
    public void KingTrumpShouldWinWithClubsTrump(){
        List<Card> cardsPlay = new ArrayList<>();
        cardsPlay.add(new Card(SuitCard.Hearts, ValueCard.Seven));
        cardsPlay.add(new Card(SuitCard.Diamonds, ValueCard.Ace));
        cardsPlay.add(new Card(SuitCard.Hearts, ValueCard.King));
        cardsPlay.add(new Card(SuitCard.Clubs, ValueCard.King));

        Card masterCard = Rule.getMasterCard(cardsPlay, SuitCard.Clubs);
        Assert.assertTrue(masterCard.equals(new Card(SuitCard.Clubs, ValueCard.King)));
    }

    @Test
    public void JackTrumpShouldWinWithClubsTrump(){
        List<Card> cardsPlay = new ArrayList<>();
        cardsPlay.add(new Card(SuitCard.Hearts, ValueCard.Seven));
        cardsPlay.add(new Card(SuitCard.Clubs, ValueCard.Jack));
        cardsPlay.add(new Card(SuitCard.Hearts, ValueCard.King));
        cardsPlay.add(new Card(SuitCard.Clubs, ValueCard.King));

        Card masterCard = Rule.getMasterCard(cardsPlay, SuitCard.Clubs);
        Assert.assertTrue(masterCard.equals(new Card(SuitCard.Clubs, ValueCard.Jack)));
    }

    @Test
    public void NineTrumpShouldWinWithHeartTrump(){
        List<Card> cardsPlay = new ArrayList<>();
        cardsPlay.add(new Card(SuitCard.Hearts, ValueCard.Seven));
        cardsPlay.add(new Card(SuitCard.Hearts, ValueCard.Ace));
        cardsPlay.add(new Card(SuitCard.Hearts, ValueCard.King));
        cardsPlay.add(new Card(SuitCard.Hearts, ValueCard.Nine));

        Card masterCard = Rule.getMasterCard(cardsPlay, SuitCard.Hearts);
        Assert.assertTrue(masterCard.equals(new Card(SuitCard.Hearts, ValueCard.Nine)));
    }
}
