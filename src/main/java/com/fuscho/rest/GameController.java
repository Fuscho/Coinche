package com.fuscho.rest;

import com.fuscho.model.card.Card;
import com.fuscho.model.card.factory.CardFactory;
import com.fuscho.model.game.Game;
import com.fuscho.model.player.HumanPlayer;
import com.fuscho.model.player.IAPlayer;
import com.fuscho.model.player.OtherPlayer;
import com.fuscho.model.player.Player;
import com.fuscho.operation.Rule;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/")
@Slf4j
public class GameController {

    @RequestMapping(method = RequestMethod.POST, value = "/init")
    public Map initGame() {
        Game game = new Game();
        IAPlayer player2 = new IAPlayer("IA1");
        IAPlayer player3 = new IAPlayer("IA2");
        IAPlayer player4 = new IAPlayer("IA3");
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

        player2.initPossibleMovesOtherPlayers();
        player3.initPossibleMovesOtherPlayers();
        player4.initPossibleMovesOtherPlayers();
        return result;
    }






}