package com.fuscho.model.game;

import com.fuscho.ia.PossibleMoves;
import com.fuscho.model.card.Card;
import com.fuscho.model.card.SuitCard;
import com.fuscho.model.player.IAPlayer;
import com.fuscho.model.player.OtherPlayer;
import com.fuscho.model.player.Player;
import com.fuscho.operation.Rule;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * Créer par mchoraine le 25/02/2016.
 */
@Slf4j
@Data
public class TurnRound {

    private final Game game;
    private List<Card> cardsOnTable = new ArrayList<>();
    private SuitCard suitAsked;
    private SuitCard trumpSuit;
    private Player winning;
    private Card masterCard;
    private Player playerTurn;

    public TurnRound(Game game, Player player, SuitCard trumpSuit){
        this.playerTurn = player;
        this.trumpSuit = trumpSuit;
        this.game = game;
    }

    public void play(Player player, Card card){
        if(player.equals(playerTurn)){
            //Update all ponderation for all ia players
//            updatePonderation(player,card);
            if(cardsOnTable.size() == 0){
                cardsOnTable.add(card);
                suitAsked = card.getSuit();
                masterCard = card;
            } else {
                cardsOnTable.add(card);
                masterCard = Rule.getMasterCard(cardsOnTable, trumpSuit);
            }
            if(card.equals(masterCard)){
                winning = player;
            }
        } else {
            log.error("Not this player turn");
        }
    }

    public Boolean isPartenaireMaster(Player player) {
        return winning != null && winning.equals(game.getPlayerPartner(player));
    }

    public void winnerCollectCards() {
        winning.addCardsWin(cardsOnTable);
    }

    public void updatePonderation(Player playerPlayCard, Card cardPlay){
        //(TurnRound turnRound, Player playerPlayCard,Player iaPlayer, Card cardPlay, OtherPlayer otherPlayer)
        game.getPlayers().stream().filter(player -> player instanceof IAPlayer && player != playerPlayCard).forEach(player -> {
            //(TurnRound turnRound, Player playerPlayCard,Player iaPlayer, Card cardPlay, OtherPlayer otherPlayer)
            IAPlayer iaPlayer = (IAPlayer) player;
            for (OtherPlayer otherPlayer : iaPlayer.getOtherPlayers()) {
                PossibleMoves.updatePossibleMove(this, playerPlayCard, iaPlayer, cardPlay, otherPlayer);
            }
        });
    }
}
