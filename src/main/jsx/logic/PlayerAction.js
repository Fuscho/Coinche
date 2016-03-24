var GameLogic = require('../logic/GameLogic.js');


module.exports = {

    addIAinRoom : function(idRoom){
        GameLogic.addIAinRoom(idRoom);
    },

    userJoinRoom : function(idRoom){
        GameLogic.userJoinRoom(idRoom);
    },

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