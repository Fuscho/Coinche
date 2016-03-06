package com.fuscho.model.player;

import com.fuscho.model.card.Card;
import lombok.Data;

import java.util.HashMap;

/**
 * Créer par mchoraine le 25/02/2016.
 */
@Data
public class OtherPlayer{

    public Player player;
    public HashMap<Card,Double> possibleMoves;

    public String toString(){
        return possibleMoves.toString();
    }
}
