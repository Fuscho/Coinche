package com.fuscho.service;

import com.fuscho.model.card.Card;
import com.fuscho.model.card.SuitCard;
import com.fuscho.model.game.*;
import com.fuscho.model.notification.*;
import com.fuscho.model.player.HumanPlayer;
import com.fuscho.model.player.IAPlayer;
import com.fuscho.model.player.Player;
import com.fuscho.operation.Rule;
import com.fuscho.websocket.MessageBuilder;
import com.fuscho.websocket.StompMessagingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Créer par mchoraine le 05/03/2016.
 */
@Service
@Slf4j
public class GameLogicService {

    @Autowired
    private StompMessagingService messagingService;

    private Map<String, Game> games = new HashMap<>();


    public Game getGame(String gameID) {
        return games.get(gameID);
    }

    public void playCard(Game game, Player player, Card cardPlay) {
        RoundGame roundGame = game.getCurrentRound();
        roundGame.playerPlayCard(player, cardPlay);
        messagingService.send(MessageBuilder.message(new CardPlayEvent(cardPlay, player.getName())));
        Player nextPlayerTurn = roundGame.getCurrentTurn().getPlayerTurn();
        if (!roundGame.isEndRound()) {
            if (nextPlayerTurn instanceof IAPlayer) {
                Card randomCard = nextPlayerTurn.getRandomCard(roundGame.getCurrentTurn());
                playCard(game, nextPlayerTurn, randomCard);
            } else if (nextPlayerTurn instanceof HumanPlayer) {
                messagingService.send(nextPlayerTurn.getName(), MessageBuilder.message(new PlayerTurnEvent(Rule.getPossibleMoves(nextPlayerTurn.getCards(), roundGame.getCurrentTurn().getSuitAsked(), roundGame.getCurrentTurn().getTrumpSuit(), roundGame.getCurrentTurn().getMasterCard(), roundGame.getCurrentTurn().isPartenaireMaster(nextPlayerTurn)))));
            }
        } else {
            game.endRound();
            Integer bidderScore = game.getTeamManager().getPlayerTeam(roundGame.getContractRound().getBidder()).getRoundScore();
            Integer otherScore = game.getTeamManager().getAdversaryTeam(roundGame.getContractRound().getBidder()).getRoundScore();
            Integer askedPoint = game.getCurrentRound().getContractRound().getFinalContract().getAskedPoint().getNbPoint();
            boolean success = bidderScore >= askedPoint;
            messagingService.send(MessageBuilder.message(new EndRoundEvent(success, bidderScore, otherScore)));
            this.newRound(game);
        }
    }

    public void bettingRound(Game game, Player player, Bidding bid) {
        RoundGame roundGame = game.getCurrentRound();
        ContractPoint bidPoint = null;
        SuitCard suit = null;
        if (bid.getValue() != null) {
            bidPoint = ContractPoint.fromValue(bid.getValue());
        }
        if (bid.getValue() != null) {
            suit = SuitCard.valueOf(bid.getSuit());
        }
        roundGame.playerBid(player, bidPoint, suit);
        messagingService.send(MessageBuilder.message(new BidEvent(player.getName(), bid)));
        if (roundGame.getContractRound().getFinalContract() != null) {
            Player playerTurn = game.getPlayers().get(game.getStartingPlayer());
            roundGame.startTurn(playerTurn);
            List<Card> possibleMoves = Rule.getPossibleMoves(playerTurn.getCards(), roundGame.getCurrentTurn().getSuitAsked(), roundGame.getCurrentTurn().getTrumpSuit(), roundGame.getCurrentTurn().getMasterCard(), roundGame.getCurrentTurn().isPartenaireMaster(playerTurn));
            messagingService.send(MessageBuilder.message(new PlayerTurnEvent(possibleMoves)));
        } else {
            Player nextPlayer = game.getNextPlayer(player);
            log.info("{} has to bid", player.getName());
            if (nextPlayer instanceof HumanPlayer) {
                messagingService.send(nextPlayer.getName(), MessageBuilder.message(new PlayerBidTurnEvent()));
            } else {
                bettingRound(game, nextPlayer, new Bidding(BidAction.PASS, null, null));
            }
        }
    }

    public void startGame(List<Player> players, String idRoom) {
        games.put(idRoom, new Game(players));
        Game game = games.get(idRoom);
        game.launchGame();
        game.startRound();
        game.getPlayers().forEach(player -> {
            if (player instanceof HumanPlayer) {
                messagingService.send(player.getName(), MessageBuilder.message(new GameStartedEvent(idRoom, player.getName(), players)));
                messagingService.send(player.getName(), MessageBuilder.message(new PlayerCardsEvent(player.getCards())));
            }
        });
        Player startingPlayer = game.getPlayers().get(game.getStartingPlayer());
        messagingService.send(startingPlayer.getName(), MessageBuilder.message(new PlayerBidTurnEvent()));
    }

    public void newRound(Game game) {
        game.startRound();
        game.getPlayers().forEach(player -> {
            if (player instanceof HumanPlayer) {
                messagingService.send(player.getName(), MessageBuilder.message(new PlayerCardsEvent(player.getCards())));
            }
        });
        Player startingPlayer = game.getPlayers().get(game.getStartingPlayer());
        messagingService.send(startingPlayer.getName(), MessageBuilder.message(new PlayerBidTurnEvent()));
    }
}
