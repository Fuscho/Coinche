package com.fuscho.rest;

import com.fuscho.model.card.Card;
import com.fuscho.model.card.SuitCard;
import com.fuscho.model.card.ValueCard;
import com.fuscho.model.game.Game;
import com.fuscho.model.game.RoundGame;
import com.fuscho.model.player.Player;
import com.fuscho.operation.Rule;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/api/")
@Slf4j
public class CardController {


    @RequestMapping(method = RequestMethod.POST, value = "/play", produces = "application/json")
    public Map playCard(@RequestBody Map card) {
        Card cardToPlay = new Card(SuitCard.valueOf(String.valueOf(card.get("suit"))), ValueCard.valueOf(String.valueOf(card.get("value"))));
        Player humanPlayer = Game.getInstance().getPlayers().get(0);
        RoundGame roundGame = Game.getInstance().getCurrentRound();
        roundGame.playerPlayCard(humanPlayer, cardToPlay);
        roundGame.nextPlayer(Game.getInstance());
        while(roundGame.getCurrentTurn().getPlayerTurn() != humanPlayer && !roundGame.endTour){
            Card randomCard = roundGame.getCurrentTurn().getPlayerTurn().getRandomCard(roundGame.getCurrentTurn());
            roundGame.playerPlayCard(roundGame.getCurrentTurn().getPlayerTurn(), randomCard);
            roundGame.nextPlayer(Game.getInstance());
        }
        Map<String, Object> result = new HashMap<>();
        if(!roundGame.endTour){
            result.put("cards", humanPlayer.getCards());
            result.put("lastTrick", roundGame.getLastTrick());
            result.put("playableCards", Rule.getPossibleMoves(humanPlayer.getCards(), roundGame.getCurrentTurn().getSuitAsked(), roundGame.getCurrentTurn().getTrumpSuit(), roundGame.getCurrentTurn().getMasterCard(), roundGame.getCurrentTurn().isPartenaireMaster(humanPlayer)));
            result.put("cardsPlay", roundGame.getCurrentTurn().getCardsOnTable());
        } else {
            result.put("cards", new ArrayList<>());
            result.put("playableCards", new ArrayList<>());
            result.put("lastTrick", roundGame.getLastTrick());
            result.put("score", roundGame.getScore());
        }
        return result;
    }

}