var GameLogic = require('../logic/GameLogic.js');


module.exports = {

    bidding : function(playerBid){
        GameLogic.playerBidding(playerBid);
    },

    playCard : function(cardPlay){
        GameLogic.playCard(cardPlay);
    },

    goNextTurn : function(){
        GameLogic.goNextTurn();
    }

};