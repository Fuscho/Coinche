package com.fuscho.rest;

import com.fuscho.model.card.Card;
import com.fuscho.model.card.SuitCard;
import com.fuscho.model.card.ValueCard;
import com.fuscho.model.game.Game;
import com.fuscho.model.player.Player;
import com.fuscho.service.GameLogicService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/api/")
@Slf4j
public class CardController {

    @Autowired
    private GameLogicService gameLogicService;

    @RequestMapping(method = RequestMethod.POST, value = "/play", produces = "application/json")
    public List<Card> playCard(@RequestBody Map card) {
        Card cardToPlay = new Card(SuitCard.valueOf(String.valueOf(card.get("suit"))), ValueCard.valueOf(String.valueOf(card.get("value"))));
        Player humanPlayer = Game.getInstance().getPlayers().get(0);
        gameLogicService.playCard(Game.getInstance(), humanPlayer, cardToPlay);
        return humanPlayer.getCards();
    }

}