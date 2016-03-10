var eventEmitter = require('events').EventEmitter;
var assign = require('object-assign');

var STATE_CHANGE_EVENT = 'state.changed';

var _currentPlayerCards = [];
var _currentPlayerSelectableCards = [];
var _cardsOnTable = [];
var _cardsOnTableQueue = [];
var _cardsHistory = [];
var _endTurn = false;
var _isBiddingMode = false;

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

    setBiddingMode : function(isBiddingMode){
        _isBiddingMode = isBiddingMode;
        store.emitStateChange();
    },

    isBiddingMode : function(){
        return _isBiddingMode;
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
    }

});

module .exports = store;