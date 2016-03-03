package com.fuscho.ia;

/**
 * Created by a614808 on 03/03/2016.
 */
public class MoveToPlay {

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


}
