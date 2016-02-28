package com.fuscho.rest;

import com.fuscho.model.card.Card;
import com.fuscho.model.game.Game;
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
public class GameController {

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



}