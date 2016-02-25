package com.fuscho;

import com.fuscho.model.card.SuitCard;
import com.fuscho.model.game.ContractRound;
import com.fuscho.model.game.Game;
import com.fuscho.model.game.RoundGame;
import com.fuscho.model.player.Player;
import org.junit.Test;

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
        roundGame.playerPlayCard(player1, player1.getCards().get(3));
        roundGame.playerPlayCard(player2, player2.getCards().get(2));
    }
}
