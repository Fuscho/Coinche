package com.fuscho.model.notification;

import com.fuscho.model.game.Bidding;
import lombok.Getter;

/**
 * Créer par mchoraine le 05/03/2016.
 */
public class BidEvent implements Event {

    @Getter
    private Integer player;

    @Getter
    private Bidding bid;

    public BidEvent(Integer player, Bidding bid) {
        this.player = player;
        this.bid = bid;
    }
}
