package com.fuscho.operation;

import com.fuscho.model.card.Card;
import com.fuscho.model.card.SuitCard;
import com.fuscho.model.game.ContractPoint;
import com.fuscho.model.game.ContractRound;
import com.fuscho.model.game.Game;
import com.fuscho.model.player.Player;
import com.fuscho.model.player.Team;
import com.fuscho.model.player.TeamManager;

import java.util.List;

/**
 * Created by a614808 on 04/03/2016.
 */
public class Score {

    public static Integer valueOfTurn(List<Card> cards, SuitCard trumpSuit){
        Integer point = cards.stream().mapToInt(card -> card.getCardValueScore(trumpSuit)).sum();
        return point;
    }

    public static void valueOfRound(List<Team> teams, ContractRound contractRound, Player playerWinning){
        //GET TEAM BIDDING
        Team teamBidding = teams.stream().filter(team -> team.isPlayerInTeam(contractRound.getBidder())).findFirst().get();
        Team otherTeam = teams.stream().filter(team -> !team.isPlayerInTeam(contractRound.getBidder())).findFirst().get();
        //10 de d'ère
        Team teamWinnningLastTurn = teams.stream().filter(team -> team.isPlayerInTeam(playerWinning)).findFirst().get();
        teamWinnningLastTurn.addToRoundScore(10);

        //Check if score team bidding is >= to the contract
        if(teamBidding.getRoundScore()>=contractRound.getAskedPoint().getNbPoint()){

            teamBidding.addToTotalScore(teamBidding.getRoundScore()+contractRound.getAskedPoint().getNbPoint());
            otherTeam.addToTotalScore(otherTeam.getRoundScore());

        }else{
            otherTeam.addToTotalScore(162+contractRound.getAskedPoint().getNbPoint());
        }

/*
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
   */
    }
}
