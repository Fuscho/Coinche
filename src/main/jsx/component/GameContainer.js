var React = require('react');
var Player = require('./Player.js');
var CurrentPlayer = require('./CurrentPlayer.js');
var GameBoard = require('./GameBoard.js');
var Score = require('./Score.js');
var Store = require("../store/Store.js");

function getStateFromStore() {
    return {
        playerCards: Store.getCurrentPlayerCards(),
        playerSelectableCards : Store.getCurrentPlayerSelectableCard(),
        biddingMode : Store.isBiddingMode(),
        cardsOnTable : Store.getCardsOnTable()
    }
}

var GameContainer = React.createClass({

    getInitialState: function() {
        var state = getStateFromStore();
        return state;
    },

    _onStateChange: function() {
        var state = getStateFromStore();
        this.setState(state);
    },

    componentDidMount: function() {
        Store.addStateChangeListener(this._onStateChange);
    },

    componentWillUnmount: function() {
        Store.removeStateChangeListener(this._onStateChange);
    },

    render : function(){
        return (
            <div className="game-container">
                <Player position="top"/>
                <Player position="left"/>
                <Player position="right"/>
                <CurrentPlayer cards={this.state.playerCards} selectableCards={this.state.playerSelectableCards}/>
                <GameBoard biddingMode={this.state.biddingMode} cardsOnTable={this.state.cardsOnTable}/>
                <Score/>
            </div>
        )
    }
});

module.exports = GameContainer;