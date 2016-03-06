package com.fuscho.model.game;

import com.fuscho.model.card.Card;
import com.fuscho.model.card.SuitCard;
import com.fuscho.model.player.Player;
import com.fuscho.model.player.Team;
import com.fuscho.model.player.TeamManager;
import com.fuscho.operation.Score;
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

    public RoundGame(){}

    public void playerPlayCard(Player player, Card card) {
        log.info("{} play : {}", player, card);
        currentTurn.play(player, card);
        player.playThisCard(card);
        nextPlayer();
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

    public void nextPlayer() {
        if(currentTurn.getCardsOnTable().size() < 4){
            currentTurn.setPlayerTurn(Game.getInstance().getNextPlayer(currentTurn.getPlayerTurn()));
        } else {
            log.info("We have a winner : {} {}", currentTurn.getMasterCard(), currentTurn.getWinning() );
            currentTurn.winnerCollectCards();
            lastTrick = currentTurn.getCardsOnTable();
            //Calculate TurnScore
            Team winnerTeam = Game.getInstance().getTeamManager().getPlayerTeam(currentTurn.getWinning());
            winnerTeam.addToRoundScore(Score.valueOfTurn(currentTurn.getCardsOnTable(),currentTurn.getTrumpSuit()));

            if(currentTurn.getWinning().getCards().size() != 0){
                //END OF TURN
                startTurn(currentTurn.getWinning());

            } else {
                //END OF ROUND

                endRound = true;
                //Calculate total score for each team
                Score.valueOfRound(Game.getInstance().getTeamManager().getTeams(),contractRound,currentTurn.getWinning());

            }
        }
    }

   /* public Integer countScore() {
        Integer bidderPoint = contractRound.getBidder().getCardsWin().stream().mapToInt(card -> card.getCardValueScore(contractRound.getTrumpSuit())).sum();
        Player playerPartner = Game.getInstance().getPlayerPartner(contractRound.getBidder());
        Integer partnerBidderPoint = playerPartner.getCardsWin().stream().mapToInt(card -> card.getCardValueScore(contractRound.getTrumpSuit())).sum();
        Integer totalPoint = bidderPoint + partnerBidderPoint;
        if(currentTurn.getWinning().equals(contractRound.getBidder()) || currentTurn.getWinning().equals(playerPartner)){
            log.info("Dix de der");
            totalPoint += 10;
        }
        log.info("Nb point : {}", totalPoint);
        this.score = totalPoint;
        return totalPoint;
    }*/

    public List<Card> getLastTrick() {
        return lastTrick;
    }
}
