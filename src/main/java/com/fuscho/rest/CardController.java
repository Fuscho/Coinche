package com.fuscho.rest;

import com.fuscho.model.card.Card;
import com.fuscho.model.card.SuitCard;
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

    private Game game;
    private RoundGame roundGame;
    private Player humanPlayer;

    @RequestMapping(method = RequestMethod.POST, value = "/init")
    public List<Card> initGame() {
        game = new Game();
        humanPlayer = new HumanPlayer();
        Player player2 = new IAPlayer();
        Player player3 = new IAPlayer();
        Player player4 = new IAPlayer();
        game.addPlayer(humanPlayer);
        game.addPlayer(player2);
        game.addPlayer(player3);
        game.addPlayer(player4);
        game.launchGame();
        roundGame = game.startRound();
        return humanPlayer.getCards();
    }

    @RequestMapping(method = RequestMethod.POST, value = "/bid")
    public void bidRound(@RequestBody Map bid) {
        roundGame.playerBid(humanPlayer, ContractRound.ContractPoint.CENT, SuitCard.Diamonds);
    }

}