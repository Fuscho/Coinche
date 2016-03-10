var GameAPI = require('../api/GameAPI.js');
var GameWebSocket = require('../api/GameWebSocket.js');
var Store = require('../store/Store.js');
var Immutable = require('immutable');

module.exports = {
    initGame: function () {
        GameWebSocket.connectToNotification(this);
        GameAPI.initGame(this.onGameInitilized)
    },

    onGameInitilized: function (playerCards) {
        Store.setCurrentPlayerCards(Immutable.fromJS(playerCards));
        Store.setBiddingMode(true);
    },

    updatePlayerSelectableCards: function (playerSelectableCard) {
        Store.setCurrentPlayerSelectableCard(Immutable.fromJS(playerSelectableCard))
    },

    playerBidding : function(playerBid){
        GameAPI.playerBidding(playerBid, this.onPlayerBidded);
    },

    onPlayerBidded : function(){
        Store.setBiddingMode(false)
    },

    playCard : function(playCard){
        Store.setCurrentPlayerSelectableCard([])
        GameAPI.playCard(playCard, this.onCardPlayed);
    },

    onCardPlayed : function(playerCards){
        Store.setCurrentPlayerCards(Immutable.fromJS(playerCards));
    },

    showCardPlay : function(cardPlayed, player){
        var cardOnTable = Immutable.fromJS({
            card : cardPlayed,
            player : player
        });
        if (!Store.isEndTurn()) {
            Store.addCardOnTable(cardOnTable);
        } else {
            Store.addCardOnTableQueue(cardOnTable);
        }
        if (Store.getCardsOnTable().length == 4) {
            Store.setEndTurn(true);
        }
    },

    goNextTurn : function(){
        if(Store.isEndTurn()) {
            Store.removeCardsOnTable();
            Store.setCardOnTable(Store.getCardsOnTableQueue());
            Store.removeCardsOnTableQueue();
            Store.setEndTurn(false);
        }
    },

    playerHasBidded : function(res){
        console.log(res)
    }


};