package com.fuscho.rest;

import com.fuscho.model.card.Card;
import com.fuscho.model.card.SuitCard;
import com.fuscho.model.card.ValueCard;
import com.fuscho.model.game.ContractPoint;
import com.fuscho.model.game.Game;
import com.fuscho.model.game.RoundGame;
import com.fuscho.model.player.HumanPlayer;
import com.fuscho.model.player.IAPlayer;
import com.fuscho.model.player.Player;
import com.fuscho.operation.Rule;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/")
@Slf4j
public class CardController {

    @RequestMapping(method = RequestMethod.POST, value = "/init")
    public Map initGame() {
        Game game = new Game();
        Player player2 = new IAPlayer("IA1");
        Player player3 = new IAPlayer("IA2");
        Player player4 = new IAPlayer("IA3");
        game.addPlayer(new HumanPlayer("Human"));
        game.addPlayer(player2);
        game.addPlayer(player3);
        game.addPlayer(player4);
        game.launchGame();
        game.startRound();
        Player humanPlayer = game.getPlayers().get(0);
        Map<String, List<Card>> result = new HashMap<>();
        result.put("cards", humanPlayer.getCards());
        result.put("playableCards", Rule.getPossibleMoves(humanPlayer.getCards(), null, null, null, null));
        return result;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/next-round")
    public Map nexRound(){
        Game game = Game.getInstance();
        RoundGame currentRound = game.getCurrentRound();
        Player humanPlayer = Game.getInstance().getPlayers().get(0);
        Map<String, List<Card>> result = new HashMap<>();
        if(currentRound.endRound){
            game.startRound();
            result.put("cards", humanPlayer.getCards());
            result.put("playableCards", Rule.getPossibleMoves(humanPlayer.getCards(), null, null, null, null));
        } else {
            log.error("Not the end");
        }
        return result;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/bid")
    public Map bidRound(@RequestBody Map bid) {
        Player player = Game.getInstance().getPlayers().get(0);
        RoundGame currentRound = Game.getInstance().getCurrentRound();
        currentRound.playerBid(player, ContractPoint.fromValue(Integer.parseInt(String.valueOf(bid.get("value")))), SuitCard.valueOf(String.valueOf(bid.get("suit"))));
        currentRound.startTurn(player);
        Map<String, Object> result = new HashMap<>();
        result.put("finishBidding", true);
        result.put("contractPoint", currentRound.getContractRound().getAskedPoint());
        result.put("contractSuit", currentRound.getContractRound().getTrumpSuit());
        result.put("contractBidder", currentRound.getContractRound().getBidder().getName());
        return result;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/play", produces = "application/json")
    public Map playCard(@RequestBody Map card) {
        Card cardToPlay = new Card(SuitCard.valueOf(String.valueOf(card.get("suit"))), ValueCard.valueOf(String.valueOf(card.get("value"))));
        Player humanPlayer = Game.getInstance().getPlayers().get(0);
        RoundGame roundGame = Game.getInstance().getCurrentRound();
        roundGame.playerPlayCard(humanPlayer, cardToPlay);
        roundGame.nextPlayer(Game.getInstance());
        while(roundGame.getCurrentTurn().getPlayerTurn() != humanPlayer && !roundGame.endRound){
            Card randomCard = roundGame.getCurrentTurn().getPlayerTurn().getRandomCard(roundGame.getCurrentTurn());
            roundGame.playerPlayCard(roundGame.getCurrentTurn().getPlayerTurn(), randomCard);
            roundGame.nextPlayer(Game.getInstance());
        }
        Map<String, Object> result = new HashMap<>();
        if(!roundGame.endRound){
            result.put("cards", humanPlayer.getCards());
            result.put("lastTrick", roundGame.getLastTrick());
            result.put("playableCards", Rule.getPossibleMoves(humanPlayer.getCards(), roundGame.getCurrentTurn().getSuitAsked(), roundGame.getCurrentTurn().getTrumpSuit(), roundGame.getCurrentTurn().getMasterCard(), roundGame.getCurrentTurn().isPartenaireMaster(humanPlayer)));
            result.put("cardsPlay", roundGame.getCurrentTurn().getCardsOnTable());
        } else {
            Game.getInstance().endRound();
            result.put("cards", new ArrayList<>());
            result.put("playableCards", new ArrayList<>());
            result.put("lastTrick", roundGame.getLastTrick());
            result.put("score", roundGame.getScore());
        }
        return result;
    }

}