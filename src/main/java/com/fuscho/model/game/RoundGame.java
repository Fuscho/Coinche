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
    public boolean endRound = false;
    private Integer score;

    public RoundGame(){}

    public void playerPlayCard(Player player, Card card) {
        log.info("{} play : {}", player, card);
        currentTurn.play(player, card);
        player.playThisCard(card);
    }

    public void playerBid(Player player, ContractPoint bidPoint, SuitCard suit) {
        this.contractRound = ContractRound.builder()
                .bidder(player)
                .askedPoint(bidPoint)
                .trumpSuit(suit)
                .build();
    }

    public void startTurn(Player player) {
        currentTurn = new TurnRound(player, contractRound.getTrumpSuit());
        endRound = false;
    }

    public void nextPlayer(Game game) {
        if(currentTurn.getCardsOnTable().size() < 4){
            currentTurn.setPlayerTurn(game.getNextPlayer(currentTurn.getPlayerTurn()));
        } else {
            log.info("We have a winner : {} {}", currentTurn.getMasterCard(), currentTurn.getWinning() );
            currentTurn.winnerCollectCards();
            lastTrick = currentTurn.getCardsOnTable();
            if(currentTurn.getWinning().getCards().size() != 0){
                startTurn(currentTurn.getWinning());
            } else {
                endRound = true;
            }
        }
    }

    public Integer countScore() {
        List<Card> cardsWin = contractRound.getBidder().getCardsWin();
        cardsWin.addAll(contractRound.getBidder().getPartner().getCardsWin());
        Integer totalPoint = cardsWin.stream().mapToInt(card -> card.getCardValueScore(contractRound.getTrumpSuit())).sum();
        if(currentTurn.getWinning().equals(contractRound.getBidder()) || currentTurn.getWinning().equals(contractRound.getBidder().getPartner())){
            log.info("Dix de der");
            totalPoint += 10;
        }
        log.info("Nb point : {}", totalPoint);
        this.score = totalPoint;
        return totalPoint;
    }

    public List<Card> getLastTrick() {
        return lastTrick;
    }

    public Integer getScore() {
        return score;
    }
}
