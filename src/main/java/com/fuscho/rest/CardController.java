package com.fuscho.rest;

import com.fuscho.model.card.Card;
import com.fuscho.model.card.SuitCard;
import com.fuscho.model.card.ValueCard;
import com.fuscho.model.game.ContractRound;
import com.fuscho.model.game.Game;
import com.fuscho.model.game.RoundGame;
import com.fuscho.model.player.HumanPlayer;
import com.fuscho.model.player.IAPlayer;
import com.fuscho.model.player.Player;
import com.fuscho.operation.Rule;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/")
@Slf4j
public class CardController {

    @RequestMapping(method = RequestMethod.POST, value = "/init")
    public List<Card> initGame() {
        Game game = new Game();
        Player player2 = new IAPlayer();
        Player player3 = new IAPlayer();
        Player player4 = new IAPlayer();
        game.addPlayer(new HumanPlayer());
        game.addPlayer(player2);
        game.addPlayer(player3);
        game.addPlayer(player4);
        game.launchGame();
        game.startRound();
        return game.getPlayers().get(0).getCards();
    }

    @RequestMapping(method = RequestMethod.POST, value = "/bid")
    public void bidRound() {
        Player player = Game.getInstance().getPlayers().get(0);
        Game.getInstance().getCurrentRound().playerBid(player, ContractRound.ContractPoint.CENT, SuitCard.Diamonds);
        Game.getInstance().getCurrentRound().startTurn(player);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/play", produces = "application/json")
    public Map playCard(@RequestBody Map card) {
        Card cardToPlay = new Card(SuitCard.valueOf(String.valueOf(card.get("suit"))), ValueCard.valueOf(String.valueOf(card.get("value"))));
        Player humanPlayer = Game.getInstance().getPlayers().get(0);
        RoundGame roundGame = Game.getInstance().getCurrentRound();
        roundGame.playerPlayCard(humanPlayer, cardToPlay);
        roundGame.nextPlayer(Game.getInstance());
        while(roundGame.getCurrentTurn().getPlayerTurn() != humanPlayer){
            roundGame.playerPlayCard(roundGame.getCurrentTurn().getPlayerTurn(), roundGame.getCurrentTurn().getPlayerTurn().getRandomCard(roundGame.getCurrentTurn()));
            roundGame.nextPlayer(Game.getInstance());
        }
        Map<String, List<Card>> result = new HashMap<>();
        result.put("cards", humanPlayer.getCards());
        result.put("playableCards", Rule.getPossibleMoves(humanPlayer.getCards(), roundGame.getCurrentTurn().getSuitAsked(), roundGame.getCurrentTurn().getTrumpSuit(), roundGame.getCurrentTurn().getMasterCard(), roundGame.getCurrentTurn().isPartenaireMaster(humanPlayer)));
        result.put("cardsPlay", roundGame.getCurrentTurn().getCardsOnTable());
        return result;
    }

}