package com.fuscho.model.game;

import com.fuscho.model.card.SuitCard;
import com.fuscho.model.player.Player;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * Créer par mchoraine le 25/02/2016.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContractRound {
    private Contract finalContract;
    private List<Contract> contractHistory = new ArrayList<>();

    public SuitCard getTrumpSuit(){
        return finalContract.getTrumpSuit();
    }

    public ContractPoint getAskedPoint(){
        return finalContract.getAskedPoint();
    }

    public Player getBidder(){
        return finalContract.getBidder();
    }

    public void addContactToHistory(Contract contract){
        contractHistory.add(contract);
        isBiddingFinish();
    }

    public void isBiddingFinish() {
        if(contractHistory.size() >= 4){
            if(contractHistory.get(contractHistory.size() -1) == null && contractHistory.get(contractHistory.size() -2) == null && contractHistory.get(contractHistory.size() -3) == null){
                finalContract = contractHistory.get(contractHistory.size() - 4);
            }
        }
    }
}