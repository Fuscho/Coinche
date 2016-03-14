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
        bid : Store.getBid(),
        mode : Store.getMode(),
        cardsOnTable : Store.getCardsOnTable(),
        score : Store.getScore()
    }
}

var GameContainer = React.createClass({

    getInitialState: function() {
        return getStateFromStore();
    },

    _onStateChange: function() {
        this.setState(getStateFromStore());
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
                <Player position="top" bid={this.state.bid} player="2"/>
                <Player position="left" bid={this.state.bid} player="1"/>
                <Player position="right" bid={this.state.bid} player="3"/>
                <CurrentPlayer cards={this.state.playerCards} selectableCards={this.state.playerSelectableCards} bid={this.state.bid} bid={this.state.bid} player="0"/>
                <GameBoard mode={this.state.mode} cardsOnTable={this.state.cardsOnTable} score={this.state.score}/>
                <Score score={this.state.score}/>
            </div>
        )
    }
});

module.exports = GameContainer;