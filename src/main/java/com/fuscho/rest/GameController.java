package com.fuscho.rest;

import com.fuscho.model.card.Card;
import com.fuscho.model.game.Game;
import com.fuscho.model.game.RoundGame;
import com.fuscho.model.player.HumanPlayer;
import com.fuscho.model.player.IAPlayer;
import com.fuscho.model.player.Player;
import com.fuscho.service.AuthentificationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/api/")
@Slf4j
public class GameController {

    @RequestMapping(method = RequestMethod.POST, value = "/init")
    public List<Card> initGame() {
        // Create a game and add players
//        Game game = new Game(users);
//        IAPlayer player2 = new IAPlayer("IA1");
//        IAPlayer player3 = new IAPlayer("IA2");
//        IAPlayer player4 = new IAPlayer("IA3");
//        game.addPlayer(new HumanPlayer(AuthentificationService.getAuthUser()));
//        game.addPlayer(player2);
//        game.addPlayer(player3);
//        game.addPlayer(player4);
//        // Create team and shuffle cards
//        game.launchGame();
//        // Deal cards
//        game.startRound();
//        Player humanPlayer = game.getPlayers().get(0);
//        Map<String, List<Card>> result = new HashMap<>();
//        result.put("cards", humanPlayer.getCards());
////        result.put("playableCards", Rule.getPossibleMoves(humanPlayer.getCards(), null, null, null, null));
//
////        player2.initPossibleMovesOtherPlayers();
////        player3.initPossibleMovesOtherPlayers();
////        player4.initPossibleMovesOtherPlayers();
//        return humanPlayer.getCards();
        return null;
    }

//    @RequestMapping(method = RequestMethod.POST, value = "/next-round")
//    public List<Card> nexRound(){
//        Game game = Game.getInstance();
//        RoundGame currentRound = game.getCurrentRound();
//        Player humanPlayer = Game.getInstance().getPlayers().get(0);
//        List<Card> result = humanPlayer.getCards();
//        if(currentRound.endRound){
//            game.startRound();
//        } else {
//            log.error("Not the end");
//        }
//        return result;
//    }






}