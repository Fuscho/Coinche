package com.fuscho.model.game;

import com.fuscho.model.card.Card;
import com.fuscho.model.card.SuitCard;
import com.fuscho.model.player.Player;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * Créer par mchoraine le 25/02/2016.
 */
@Data
@Slf4j
public class RoundGame {
    private ContractRound contractRound;
    private TurnRound currentTurn;
    private List<Card> lastTrick = new ArrayList<>();
    public boolean endTour = false;

    public RoundGame(){}

    public void playerPlayCard(Player player, Card card) {
        log.info("{} play : {}", player, card);
        currentTurn.play(player, card);
        player.playThisCard(card);
    }

    public void playerBid(Player player, ContractRound.ContractPoint bidPoint, SuitCard suit) {
        this.contractRound = ContractRound.builder()
                .bidder(player)
                .askedPoint(bidPoint)
                .trumpSuit(suit)
                .build();
    }

    public void startTurn(Player player) {
        currentTurn = new TurnRound(player, contractRound.getTrumpSuit());
        endTour = false;
    }

    public void nextPlayer(Game game) {
        if(currentTurn.getCardsOnTable().size() < 4){
            currentTurn.setPlayerTurn(game.getNextPlayer(currentTurn.getPlayerTurn()));
        } else {
            log.info("We have a winner : {} {}", currentTurn.getMasterCard(), currentTurn.getWinning() );
            currentTurn.winnerCollectCards();
            if(currentTurn.getWinning().getCards().size() != 0){
                lastTrick = currentTurn.getCardsOnTable();
                startTurn(currentTurn.getWinning());
            } else {
                endRound();
            }
        }
    }

    private void endRound() {
        endTour = true;
        countScore();
    }

    public Integer countScore() {
        List<Card> cardsWin = contractRound.getBidder().getCardsWin();
        cardsWin.addAll(contractRound.getBidder().getPartner().getCardsWin());
        Integer totalPoint = cardsWin.stream().mapToInt(card -> card.getPoint(contractRound.getTrumpSuit())).sum();
        log.info("Nb point : {}", totalPoint);
        return totalPoint;
    }

    public List<Card> getLastTrick() {
        return lastTrick;
    }
}
