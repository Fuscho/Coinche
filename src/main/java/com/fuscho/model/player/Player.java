package com.fuscho.model.player;

import com.fuscho.model.card.Card;

import java.util.List;

/**
 * Créer par mchoraine le 25/02/2016.
 */
public class Player {
    private List<Card> cards;
    private OtherPlayer adversary1;
    private OtherPlayer adversary2;
    private OtherPlayer crewMan;
    private Boolean human;
}
