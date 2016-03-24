package com.fuscho.model.game;

import com.fuscho.model.User;
import com.fuscho.model.card.Card;
import com.fuscho.model.card.SuitCard;
import com.fuscho.model.card.ValueCard;
import com.fuscho.model.player.HumanPlayer;
import com.fuscho.model.player.Player;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Créer par mchoraine le 27/02/2016.
 */
public class RoundGameTest {

    @Test
    public void shouldCountScore(){
        Player player1 = new HumanPlayer(new User("IA1"));
        Player player2 = new HumanPlayer(new User("IA2"));
        Player player3 = new HumanPlayer(new User("IA3"));
        Player player4 = new HumanPlayer(new User("IA4"));

        player1.addCards(new ArrayList<Card>(){{add(new Card(SuitCard.Hearts, ValueCard.Ace));}});
        player2.addCards(new ArrayList<Card>(){{add(new Card(SuitCard.Hearts, ValueCard.Jack));}});
        player3.addCards(new ArrayList<Card>(){{add(new Card(SuitCard.Clubs, ValueCard.Ten));}});
        player4.addCards(new ArrayList<Card>(){{add(new Card(SuitCard.Hearts, ValueCard.Queen));}});

        Game game = new Game(Arrays.asList(player1, player2, player3, player4));
        game.addPlayer(player1);
        game.addPlayer(player2);
        game.addPlayer(player3);
        game.addPlayer(player4);

        game.launchGame();
        RoundGame roundGame = game.startRound();
        game.getPlayers().stream().forEach(player -> player.setCards(new ArrayList<>()));
        roundGame.playerBid(player1, null, null);
        roundGame.playerBid(player2, ContractPoint.CENT, SuitCard.Hearts);
        roundGame.playerBid(player3, null, null);
        roundGame.playerBid(player4, null, null);
        roundGame.playerBid(player1, null, null);
        roundGame.startTurn(player1);
        roundGame.playerPlayCard(player1, new Card(SuitCard.Hearts, ValueCard.Ace));
        roundGame.playerPlayCard(player2, new Card(SuitCard.Hearts, ValueCard.Jack));
        roundGame.playerPlayCard(player3, new Card(SuitCard.Clubs, ValueCard.Ten));
        roundGame.playerPlayCard(player4, new Card(SuitCard.Hearts, ValueCard.Queen));

        game.endRound();

        Assert.assertEquals(new Integer(54), game.getTeamManager().getPlayerTeam(roundGame.getContractRound().getBidder()).getRoundScore());
    }
}
