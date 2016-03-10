package com.fuscho.service;

import com.fuscho.model.card.Card;
import com.fuscho.model.card.SuitCard;
import com.fuscho.model.game.Bidding;
import com.fuscho.model.game.ContractPoint;
import com.fuscho.model.game.Game;
import com.fuscho.model.game.RoundGame;
import com.fuscho.model.notification.BidEvent;
import com.fuscho.model.notification.CardPlayEvent;
import com.fuscho.model.notification.EndRoundEvent;
import com.fuscho.model.notification.PlayerTurnEvent;
import com.fuscho.model.player.HumanPlayer;
import com.fuscho.model.player.IAPlayer;
import com.fuscho.model.player.Player;
import com.fuscho.operation.Rule;
import com.fuscho.websocket.MessageBuilder;
import com.fuscho.websocket.StompMessagingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Créer par mchoraine le 05/03/2016.
 */
@Service
@Slf4j
public class GameLogicService {

    @Autowired
    private StompMessagingService messagingService;

    public void playCard(Game game, Player player, Card cardPlay){
        RoundGame roundGame = game.getCurrentRound();
        roundGame.playerPlayCard(player, cardPlay);
        Integer playerPosition = game.getPlayers().indexOf(player);
        messagingService.send(MessageBuilder.message(new CardPlayEvent(cardPlay, playerPosition)));
        Player nextPlayerTurn = roundGame.getCurrentTurn().getPlayerTurn();
        if(!roundGame.isEndRound()){
            if(nextPlayerTurn instanceof IAPlayer){
                Card randomCard = nextPlayerTurn.getRandomCard(roundGame.getCurrentTurn());
                playCard(game, nextPlayerTurn, randomCard);
            } else if (nextPlayerTurn instanceof HumanPlayer){
                messagingService.send(MessageBuilder.message(new PlayerTurnEvent(Rule.getPossibleMoves(nextPlayerTurn.getCards(), roundGame.getCurrentTurn().getSuitAsked(), roundGame.getCurrentTurn().getTrumpSuit(), roundGame.getCurrentTurn().getMasterCard(), roundGame.getCurrentTurn().isPartenaireMaster(nextPlayerTurn)))));
            }
        } else {
            Game.getInstance().endRound();
            Integer score = game.getTeamManager().getPlayerTeam(roundGame.getContractRound().getBidder()).getRoundScore();
            messagingService.send(MessageBuilder.message(new EndRoundEvent(score)));
        }
    }

    public void bettingRound(Game game, Player player, Bidding bid){
        RoundGame roundGame = game.getCurrentRound();
        ContractPoint bidPoint = null;
        SuitCard suit = null;
        if(bid.getValue() != null){
            bidPoint = ContractPoint.fromValue(bid.getValue());
        }
        if(bid.getValue() != null){
            suit = SuitCard.valueOf(bid.getSuit());
        }
        roundGame.playerBid(player, bidPoint, suit);
        Integer playerPosition = game.getPlayers().indexOf(player);
        messagingService.send(MessageBuilder.message(new BidEvent(playerPosition, bid)));
        if(roundGame.getContractRound().getFinalContract() != null){
            Player playerTurn = game.getPlayers().get(0);
            roundGame.startTurn(playerTurn);
            List<Card> possibleMoves = Rule.getPossibleMoves(playerTurn.getCards(), roundGame.getCurrentTurn().getSuitAsked(), roundGame.getCurrentTurn().getTrumpSuit(), roundGame.getCurrentTurn().getMasterCard(), roundGame.getCurrentTurn().isPartenaireMaster(playerTurn));
            messagingService.send(MessageBuilder.message(new PlayerTurnEvent(possibleMoves)));
        } else {
            bettingRound(game, game.getNextPlayer(player), new Bidding(null, null));
        }
    }
}
