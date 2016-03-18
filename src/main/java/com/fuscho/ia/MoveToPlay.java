package com.fuscho.ia;

import com.fuscho.model.card.Card;
import com.fuscho.model.card.SuitCard;
import com.fuscho.model.game.Game;
import com.fuscho.model.player.IAPlayer;
import com.fuscho.model.player.Player;
import com.fuscho.operation.Rule;
import com.fuscho.operation.Score;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by a614808 on 03/03/2016.
 */
public class MoveToPlay {
    public static int infinite = 1000;
    //TODO



    //ALGO NEGAMAX

    //function negamax(les cout possibles par joueurs,alpha,beta,carteAjouer,CarteJouées)
    //Structure à REVOIR
    //On est le j1
    //On appelle c1 : cartes de j1
    //c2: carte de j2
    // ...
    //Carte a jouer est null au départ déterminée à la fin de l'algo
    //Carte jouées correspond au carte présente sur la table
    //Alpha toujours inférieur à Beta
    //Alpha : la pire valeur de pli
    //Beta : la meilleur valeur de pli

    //appel de la fonction([c1[c2[c3[c4]]]],-100,100,carteAJouer,CarteJouées)

    //Déroulement

    /**
    fonction ALPHABETA(C, A, B, ??....??)
        si C n'a pas d'enfant (dernier joueur avant la fin du pli) alors
            retourner la valeur du pli
                 Valeur d'un pli :
                 //en fonction des cartes tombées
        sinon

        Meilleur = –INFINI

        pour tout enfant Ci de C faire
            //ajout Ci dans cartes jouées
            Val = -ALPHABETA(Ci,-B,-A,...)
                //???Pondération suivant les différents cas (voir en dessous)??? je sais pas si ca va vraiment là mais je crois
            si Val > Meilleur alors
                Meilleur = Val
                si Meilleur > A alors
                    A = Meilleur
                    si A ≥ B alors
                        retourner Meilleur
                    finsi
                finsi
            finsi
            //supression Ci dans cartes jouées
        finpour

        retourner Meilleur

        finsi
    fin
     */

/**
    // Pondération suivant des cas :

    //Ex sous coupé pondération de -10...
 **/

    public static int negamax(IAPlayer iaPlayer, Player currentPlayer, List<Card> cardsToPlay, Map<Player,Card> cardsPlay, SuitCard trumpSuit, int alpha, int beta, Card cardToPlay){
        int val = 0;
        int best = - infinite;

        if(cardsPlay.size()==4){
            //Value of CardsPlay (Valeur du pli)
            //Get list of cards present on table
            List<Card> cardsOnTable = new ArrayList<>(cardsPlay.values());
            //Value of turn
            Integer valueOfTurn = Score.valueOfTurn(cardsOnTable,trumpSuit);
            //Get master Card
            Card masterCard = Rule.getMasterCard(cardsOnTable,trumpSuit);
            //Get master Player
            Player masterPlayer = cardsPlay.entrySet()
                    .stream()
                    .filter(entry -> entry.getValue()==masterCard)
                    .map(Map.Entry::getKey)
                    .findFirst().get();
            //If masterPlayer isn't partner of iaPlayer => value of turn is neg
            if(Game.getInstance().getPlayerPartner(iaPlayer)!=masterPlayer){
                valueOfTurn = - valueOfTurn;
            }
            return valueOfTurn;
        }else{
            //Get Player object  corresponding to the next player
            Player nextPlayer = Game.getInstance().getNextPlayer(currentPlayer);
            //Get next Player possible Moves (Ponderation != 0)
            List<Card> nextPlayerPossibleCards = PossibleMoves.getOtherPlayerPossibleMoves(iaPlayer,nextPlayer);
            for(Card card : cardsToPlay ){
                Map<Player,Card> cardsPlayTMP = cardsPlay;
                Card cardToPlayTMP = new Card();
                cardsPlayTMP.put(currentPlayer,card);
                //Analyse nextPLayer moves
                val = -negamax(iaPlayer,nextPlayer,nextPlayerPossibleCards,cardsPlayTMP,trumpSuit,-beta,-alpha,cardToPlayTMP);
                if(val > best){
                    cardToPlay.setSuit(card.getSuit());
                    cardToPlay.setValue(card.getValue());
                    best = val;
                    if(best > alpha){
                        alpha = best;
                        if(alpha >= beta){
                            return best;
                        }
                    }
                }
            }
            return best;
        }
    }

}
