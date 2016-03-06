package com.fuscho.rest;


import com.fuscho.model.card.SuitCard;
import com.fuscho.model.game.ContractPoint;
import com.fuscho.model.game.Game;
import com.fuscho.model.game.RoundGame;
import com.fuscho.model.player.Player;
import com.fuscho.service.GameLogicService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/")
@Slf4j
public class BidController {


    @Autowired
    private GameLogicService gameLogicService;

    @RequestMapping(method = RequestMethod.POST, value = "/bid")
    public Map bidRound(@RequestBody Map bid) {
        Player player = Game.getInstance().getPlayers().get(0);
        RoundGame currentRound = Game.getInstance().getCurrentRound();
        currentRound.playerBid(player, ContractPoint.fromValue(Integer.parseInt(String.valueOf(bid.get("value")))), SuitCard.valueOf(String.valueOf(bid.get("suit"))));
        currentRound.startTurn(player);
        gameLogicService.bettingRound(Game.getInstance(), player);
        Map<String, Object> result = new HashMap<>();
        result.put("finishBidding", true);
        result.put("contractPoint", currentRound.getContractRound().getAskedPoint());
        result.put("contractSuit", currentRound.getContractRound().getTrumpSuit());
        result.put("contractBidder", currentRound.getContractRound().getBidder().getName());
        return result;
    }


}