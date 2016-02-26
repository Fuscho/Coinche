package com.fuscho.model.game;

import com.fuscho.model.card.SuitCard;
import com.fuscho.model.player.Player;
import lombok.Builder;
import lombok.Data;

/**
 * Créer par mchoraine le 25/02/2016.
 */
@Builder
@Data
public class ContractRound {
    private SuitCard trumpSuit;
    private ContractPoint askedPoint;
    private Player bidder;


    public enum ContractPoint{
        QUATREVINGTS, QUATREVINGTSDIX, CENT, CENTDIX, CENTVINGTS, CENTTRENTE, CENTQUARANTE, CINTCINQUANTE, CAPOT;
    }
}