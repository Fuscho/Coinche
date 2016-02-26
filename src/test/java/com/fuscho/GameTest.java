package com.fuscho;

import com.fuscho.model.card.Card;
import com.fuscho.model.card.SuitCard;
import com.fuscho.model.game.ContractRound;
import com.fuscho.model.game.Game;
import com.fuscho.model.game.RoundGame;
import com.fuscho.model.game.TurnRound;
import com.fuscho.model.player.Player;
import com.fuscho.operation.Rule;
import org.junit.Test;

import java.util.List;

/**
 * Créer par mchoraine le 25/02/2016.
 */
public class GameTest {

    @Test
    public void shouldPlay(){
        Game game = new Game();
        Player player1 = new Player();
        Player player2 = new Player();
        game.addPlayer(player1);
        game.addPlayer(player2);
        game.addPlayer(new Player());
        game.addPlayer(new Player());
        game.launchGame();
        RoundGame roundGame = game.startRound();
        roundGame.playerBid(player1, ContractRound.ContractPoint.CENT, SuitCard.Diamonds);
        roundGame.startTurn(player1);
        TurnRound currentTurn = roundGame.getCurrentTurn();
        ContractRound contractRound = roundGame.getContractRound();
//        roundGame.playerPlayCard(player1, getRandomCard(player1.getCards(), currentTurn.getSuitAsked(), contractRound.getTrumpSuit());
        roundGame.playerPlayCard(player2, player2.getCards().get(2));
    }


//    private Card getRandomCard(List<Card> cards, SuitCard askedSuit, SuitCard trumpSuit, Card masterCard, Boolean partnerIsMaster){
//        Rule.getPossibleMoves(cards)
//    }
}
