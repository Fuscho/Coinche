package com.fuscho.rest;


import com.fuscho.model.game.Bidding;
import com.fuscho.model.game.Game;
import com.fuscho.model.player.Player;
import com.fuscho.service.AuthentificationService;
import com.fuscho.service.GameLogicService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/")
@Slf4j
public class BidController {


    @Autowired
    private GameLogicService gameLogicService;

    @RequestMapping(method = RequestMethod.POST, value = "/bid/{gameID}")
    public void bidRound(@RequestBody Bidding bid, @PathVariable String gameID) {
        Game game = gameLogicService.getGame(gameID);
        Player player = game.getPlayer(AuthentificationService.getAuthUser().getPseudo());
        gameLogicService.bettingRound(game, player, bid);
    }


}