package com.fuscho;

import com.fuscho.model.card.Card;
import com.fuscho.model.card.SuitCard;
import com.fuscho.model.game.ContractRound;
import com.fuscho.model.game.Game;
import com.fuscho.model.game.RoundGame;
import com.fuscho.model.game.TurnRound;
import com.fuscho.model.player.HumanPlayer;
import com.fuscho.model.player.Player;
import com.fuscho.operation.Rule;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

/**
 * Créer par mchoraine le 25/02/2016.
 */
public class GameTest {

    @Test
    public void shouldPlay(){
        // Create a game
        Game game = new Game();
        // Add player to game
        Player player1 = new HumanPlayer();
        Player player2 = new HumanPlayer();
        Player player3 = new HumanPlayer();
        Player player4 = new HumanPlayer();
        game.addPlayer(player1);
        game.addPlayer(player2);
        game.addPlayer(player3);
        game.addPlayer(player4);
        // Launch the game and round
        game.launchGame();
        RoundGame roundGame = game.startRound();
        // Start bidding
        roundGame.playerBid(player1, ContractRound.ContractPoint.CENT, SuitCard.Diamonds);
        // Start turn
        roundGame.startTurn(player1);
        TurnRound currentTurn = roundGame.getCurrentTurn();
        ContractRound contractRound = roundGame.getContractRound();
        // Start play
        Card player1FirstCard = player1.getCards().get(0);
        roundGame.playerPlayCard(player1, player1FirstCard);
        roundGame.nextPlayer(game);
        roundGame.playerPlayCard(player2, getRandomCard(player2.getCards(), currentTurn.getSuitAsked(), contractRound.getTrumpSuit(), Rule.getMasterCard(currentTurn.getCardsOnTable(), contractRound.getTrumpSuit()), currentTurn.isPartenaireMaster(player2)));
        roundGame.nextPlayer(game);
        roundGame.playerPlayCard(player3, getRandomCard(player3.getCards(), currentTurn.getSuitAsked(), contractRound.getTrumpSuit(), Rule.getMasterCard(currentTurn.getCardsOnTable(), contractRound.getTrumpSuit()), currentTurn.isPartenaireMaster(player2)));
        roundGame.nextPlayer(game);
        roundGame.playerPlayCard(player4, getRandomCard(player4.getCards(), currentTurn.getSuitAsked(), contractRound.getTrumpSuit(), Rule.getMasterCard(currentTurn.getCardsOnTable(), contractRound.getTrumpSuit()), currentTurn.isPartenaireMaster(player2)));
        //Everybody should have play
        Assert.assertEquals(currentTurn.getCardsOnTable().size(), 4);
        Assert.assertEquals(player1FirstCard, currentTurn.getCardsOnTable().get(0));
        Assert.assertEquals(player1FirstCard.getSuit(), currentTurn.getSuitAsked());
        Assert.assertEquals(SuitCard.Diamonds, currentTurn.getTrumpSuit());
        Assert.assertEquals(player1.getCards().size(), 7);
        // Go to next turn
        roundGame.nextPlayer(game);
        Assert.assertEquals(roundGame.getCurrentTurn().getPlayerTurn().getCardsWin().size(), 4);

        for (int i = 0; i < 4*7; i++) {
            roundGame.playerPlayCard(roundGame.getCurrentTurn().getPlayerTurn(), getRandomCard(roundGame.getCurrentTurn().getPlayerTurn().getCards(), currentTurn.getSuitAsked(), contractRound.getTrumpSuit(), Rule.getMasterCard(currentTurn.getCardsOnTable(), contractRound.getTrumpSuit()), currentTurn.isPartenaireMaster(roundGame.getCurrentTurn().getPlayerTurn())));
            roundGame.nextPlayer(game);
        }
    }


    private Card getRandomCard(List<Card> cards, SuitCard askedSuit, SuitCard trumpSuit, Card masterCard, Boolean partnerIsMaster){
        List<Card> possibleMoves = Rule.getPossibleMoves(cards, askedSuit, trumpSuit, masterCard, partnerIsMaster);
        return possibleMoves.get(0);
    }
}
