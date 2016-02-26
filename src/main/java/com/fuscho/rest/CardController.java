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
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
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
    public void bidRound(@RequestBody Map bid) {
        Game.getInstance().getCurrentRound().playerBid(Game.getInstance().getPlayers().get(0), ContractRound.ContractPoint.CENT, SuitCard.Diamonds);
        Game.getInstance().getCurrentRound().startTurn(Game.getInstance().getPlayers().get(0));
    }

    @RequestMapping(method = RequestMethod.POST, value = "/play")
    public List<Card> playCard(@RequestBody Map card) {
        Card cardToPlay = new Card(SuitCard.valueOf(String.valueOf(card.get("suit"))), ValueCard.valueOf(String.valueOf(card.get("value"))));
        Player humanPlayer = Game.getInstance().getPlayers().get(0);
        log.info("{}", humanPlayer);
        log.info("{}", card);
        log.info("{}", cardToPlay);
        RoundGame roundGame = Game.getInstance().getCurrentRound();
        roundGame.playerPlayCard(humanPlayer, cardToPlay);
        roundGame.nextPlayer(Game.getInstance());
        while(roundGame.getCurrentTurn().getPlayerTurn() != humanPlayer){
            roundGame.playerPlayCard(humanPlayer, new Card(SuitCard.valueOf(String.valueOf(card.get("suit"))), ValueCard.valueOf(String.valueOf(card.get("value")))));
            roundGame.nextPlayer(Game.getInstance());
        }
        return humanPlayer.getCards();
    }

}