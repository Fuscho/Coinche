package com.fuscho.model.notification;

import com.fuscho.model.card.Card;
import com.fuscho.model.game.Bidding;
import com.fuscho.model.game.Contract;
import com.fuscho.model.player.Player;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Créer par mchoraine le 05/03/2016.
 */
public class BidEvent implements Event {

    @Getter
    private Player player;

    @Getter
    private Bidding bid;

    public BidEvent(Player player, Bidding bid) {
        this.player = player;
        this.bid = bid;
    }
}
