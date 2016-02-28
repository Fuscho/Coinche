package com.fuscho.model.game;

import com.fuscho.model.card.Card;
import com.fuscho.model.card.SuitCard;
import com.fuscho.model.card.ValueCard;
import com.fuscho.model.player.IAPlayer;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

/**
 * Créer par mchoraine le 27/02/2016.
 */
public class RoundGameTest {

    @Test
    public void shouldCountScore(){
        IAPlayer player1 = new IAPlayer("IA1");
        IAPlayer player2 = new IAPlayer("IA2");
        IAPlayer player3 = new IAPlayer("IA3");
        IAPlayer player4 = new IAPlayer("IA4");

        player1.addCards(new ArrayList<Card>(){{add(new Card(SuitCard.Hearts, ValueCard.Ace));}});
        player2.addCards(new ArrayList<Card>(){{add(new Card(SuitCard.Hearts, ValueCard.Jack));}});
        player3.addCards(new ArrayList<Card>(){{add(new Card(SuitCard.Clubs, ValueCard.Ten));}});
        player4.addCards(new ArrayList<Card>(){{add(new Card(SuitCard.Hearts, ValueCard.Queen));}});

        Game game = new Game();
        game.addPlayer(player1);
        game.addPlayer(player2);
        game.addPlayer(player3);
        game.addPlayer(player4);

        game.launchGame();
        RoundGame roundGame = game.startRound();
        game.getPlayers().stream().forEach(player -> player.setCards(new ArrayList<>()));
        roundGame.playerBid(player2, ContractPoint.CENT, SuitCard.Hearts);
        roundGame.startTurn(player1);
        roundGame.playerPlayCard(player1, new Card(SuitCard.Hearts, ValueCard.Ace));
        roundGame.nextPlayer(game);
        roundGame.playerPlayCard(player2, new Card(SuitCard.Hearts, ValueCard.Jack));
        roundGame.nextPlayer(game);
        roundGame.playerPlayCard(player3, new Card(SuitCard.Clubs, ValueCard.Ten));
        roundGame.nextPlayer(game);
        roundGame.playerPlayCard(player4, new Card(SuitCard.Hearts, ValueCard.Queen));
        roundGame.nextPlayer(game);

        game.endRound();

        Assert.assertEquals(new Integer(54), roundGame.getScore());
    }
}
