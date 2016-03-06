package com.fuscho.model.game;

import com.fuscho.model.card.SuitCard;
import com.fuscho.model.player.Player;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Contract {
    private SuitCard trumpSuit;
    private ContractPoint askedPoint;
    private Player bidder;
}