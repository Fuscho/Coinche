var eventEmitter = require('events').EventEmitter;
var assign = require('object-assign');
var Immutable = require('immutable');

var STATE_CHANGE_EVENT = 'state.changed';

var _currentPlayerCards = [];
var _currentPlayerSelectableCards = [];
var _cardsOnTable = [];
var _cardsOnTableQueue = [];
var _cardsHistory = [];
var _endTurn = false;
var _currentMode = false;
var _currentBid = null;
var _score = Immutable.fromJS([]);

var store = assign({}, eventEmitter.prototype, {

    emitStateChange: function () {
        this.emit(STATE_CHANGE_EVENT);
    },

    addStateChangeListener: function (callback) {
        this.on(STATE_CHANGE_EVENT, callback);
    },

    removeStateChangeListener: function (callback) {
        this.removeListener(STATE_CHANGE_EVENT, callback);
    },

    setCurrentPlayerCards : function(playerCards){
        _currentPlayerCards = playerCards;
        store.emitStateChange();
    },

    getCurrentPlayerCards : function(){
        return _currentPlayerCards;
    },

    setCurrentPlayerSelectableCard : function(playerSelectableCards){
        _currentPlayerSelectableCards = playerSelectableCards;
        store.emitStateChange();
    },

    getCurrentPlayerSelectableCard : function(){
        return _currentPlayerSelectableCards;
    },

    setBid : function(bid){
        _currentBid = bid;
    },

    getBid : function(){
      return _currentBid;
    },

    setMode : function(isBiddingMode){
        _currentMode = isBiddingMode;
        store.emitStateChange();
    },

    getMode : function(){
        return _currentMode;
    },

    setEndTurn : function(endTurn){
        _endTurn = endTurn;
        store.emitStateChange();
    },

    isEndTurn : function(){
        return _endTurn;
    },

    addCardOnTable : function(cardToAddOnTable){
        _cardsOnTable.push(cardToAddOnTable);
        store.emitStateChange();
    },

    getCardsOnTable : function(){
        return _cardsOnTable;
    },

    setCardOnTable : function(cardsOnTable){
        _cardsOnTable = cardsOnTable;
        store.emitStateChange();
    },

    removeCardsOnTable : function(){
        _cardsOnTable = [];
        store.emitStateChange();
    },

    addCardOnTableQueue : function(cardToAddOnTable){
        _cardsOnTableQueue.push(cardToAddOnTable);
        store.emitStateChange();
    },

    getCardsOnTableQueue : function(){
        return _cardsOnTableQueue;
    },

    removeCardsOnTableQueue : function(){
        _cardsOnTableQueue = [];
        store.emitStateChange();
    },

    addToScore : function(currentScore){
        _score = _score.push(currentScore);
    },

    getScore : function(){
        return _score;
    }

});

module .exports = store;