var GameAPI = require('../api/GameAPI.js');
var GameWebSocket = require('../api/GameWebSocket.js');
var Store = require('../store/Store.js');
var Immutable = require('immutable');

module.exports = {

    getRooms : function(){
        GameAPI.getRooms(this.onRoomsRetrieved)
    },

    onRoomsRetrieved : function(rooms){
        Store.setRooms(rooms);
    },

    addRoom : function(room){
        Store.addRoom(room);
    },

    createRoom : function(){
        GameAPI.createRoom()
    },

    addIAinRoom : function(idRoom){
        GameAPI.addIAinRoom(idRoom);
    },

    userJoinRoom : function(idRoom){
        GameAPI.userJoinRoom(idRoom);
    },

    updateRoom : function(room){
        Store.updateRoom(room);
    },

    setGameStarted : function(players, isGameStarted){
        Store.setGame(Immutable.fromJS(players));
        Store.setGameStarted(isGameStarted)
    },

    initGame: function () {
        GameWebSocket.connectToNotification(Store.getUser(), this);
    },

    onGameInitilized: function (playerCards) {
        Store.setCurrentPlayerCards(Immutable.fromJS(playerCards));
    },

    playerHasToBid : function(){
        Store.setMode("bidding");
    },

    updatePlayerSelectableCards: function (playerSelectableCard) {
        Store.setCurrentPlayerSelectableCard(Immutable.fromJS(playerSelectableCard))
    },

    playerBidding : function(playerBid){
        GameAPI.playerBidding(Store.getGame().get("idGame"), playerBid, this.onPlayerBidded);
    },

    onPlayerBidded : function(){
        Store.setMode("playing")
    },

    playerShouldBid : function(){
        Store.setMode("bidding")
    },

    playerHasBidded : function(res){
        if(res.bid.value != null && res.bid.suit != null){
            Store.setBid(Immutable.fromJS(res));
        }
    },

    playCard : function(playCard){
        Store.setCurrentPlayerSelectableCard([]);
        GameAPI.playCard(Store.getGame().get("idGame"), playCard, this.onCardPlayed);
    },

    onCardPlayed : function(playerCards){
-        Store.setCurrentPlayerCards(Immutable.fromJS(playerCards));
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
            setTimeout(this.goNextTurn, 800);
        }
    },

    goNextTurn : function(){
        if(Store.isEndTurn()) {
            Store.removeCardsOnTable();
            Store.setCardOnTable(Store.getCardsOnTableQueue());
            Store.removeCardsOnTableQueue();
            Store.setEndTurn(false);
        } else {
            GameAPI.nextRound(this.onGameInitilized)
        }
    },

    addToScore : function(currentScore){
        currentScore = Immutable.fromJS(currentScore);
        var teamScore;
        if(Store.getBid().get("player") == 0 || Store.getBid().get("player")==2){
            teamScore = {
                "you" : currentScore.get("bidderScore"),
                "other" : currentScore.get("otherScore")
            };
        } else {
            teamScore = {
                "other" : currentScore.get("bidderScore"),
                "you" : currentScore.get("otherScore")
            };
        }
        Store.addToScore(Immutable.fromJS(teamScore));
        Store.setMode("score")
    }


};