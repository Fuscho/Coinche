package com.fuscho.model.player;

import com.fuscho.model.card.Card;
import lombok.Data;

import java.util.List;

/**
 * Créer par mchoraine le 25/02/2016.
 */
@Data
public class Player {
    private List<Card> cards;
    private OtherPlayer adversary1;
    private OtherPlayer adversary2;
    private OtherPlayer partner;
    private Boolean human;
}
