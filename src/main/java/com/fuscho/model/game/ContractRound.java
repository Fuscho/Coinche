package com.fuscho.model.game;

import com.fuscho.model.card.SuitCard;
import com.fuscho.model.player.Player;
import lombok.Builder;

/**
 * Créer par mchoraine le 25/02/2016.
 */
@Builder
public class ContractRound {
    private SuitCard suit;
    private ContractPoint askedPoint;
    private Player bidder;


    public enum ContractPoint{
        QUATREVINGTS, QUATREVINGTSDIX, CENT, CENTDIX, CENTVINGTS, CENTTRENTE, CENTQUARANTE, CINTCINQUANTE, CAPOT;
    }
}