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
        QUATREVINGTS(80), QUATREVINGTSDIX(90), CENT(100), CENTDIX(110), CENTVINGTS(120), CENTTRENTE(130), CENTQUARANTE(140), CINTCINQUANTE(150), CAPOT(250);
        private final Integer nbPoint;

        ContractPoint(Integer point){
            this.nbPoint = point;
        }

        public static ContractPoint fromValue(Integer point) {
            if (point != null) {
                for (ContractPoint b : ContractPoint.values()) {
                    if (point.equals(b.nbPoint)) {
                        return b;
                    }
                }
            }
            return null;
        }
    }
}