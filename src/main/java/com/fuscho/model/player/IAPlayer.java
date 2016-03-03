package com.fuscho.model.player;

import com.fuscho.ia.PossibleMoves;
import com.fuscho.model.card.Card;
import com.fuscho.model.card.factory.CardFactory;
import com.fuscho.model.game.TurnRound;
import com.fuscho.operation.Rule;
import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Créer par mchoraine le 26/02/2016.
 */
@Data
public class IAPlayer extends Player{
    private List<OtherPlayer> otherPlayers;

    public IAPlayer(String name) {
        this.setName(name);
        this.otherPlayers = new ArrayList<>();
    }

    @Override
    public Card getRandomCard(TurnRound currentTurn) {
        List<Card> possibleMoves = Rule.getPossibleMoves(this.getCards(), currentTurn.getSuitAsked(), currentTurn.getTrumpSuit(), currentTurn.getMasterCard(), currentTurn.isPartenaireMaster(this));
        return possibleMoves.get(0);
    }

    public void initPossibleMoves(List<Player> otherPlayers){

        HashMap<Card,Double> possibleMoves = PossibleMoves.initOtherPlayerPossibleMoves(this.getCards());
        for(Player otherPlayerTMP: otherPlayers){
            OtherPlayer otherPlayer = new OtherPlayer();
            otherPlayer.setPlayer(otherPlayerTMP);
            otherPlayer.setPossibleMoves(possibleMoves);
            this.otherPlayers.add(otherPlayer);
        }
    }


}
