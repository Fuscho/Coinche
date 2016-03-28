var React = require('react');

var ResultatWindow = require("./ResultatWindow.js");
var BiddingWindow = require("./BiddingWindow.js");
var CardsOnTableContainer = require("./CardsOnTableContainer.js");

var PlayerAction = require('../logic/PlayerAction.js');

var GameBoard = React.createClass({
    render: function () {
        var mode = null;
        switch (this.props.mode) {
            case "bidding" :
                mode = (<BiddingWindow currentBid={this.props.currentBid}/>);
                break;
            case "playing" :
                mode = (<CardsOnTableContainer cardsOnTable={this.props.cardsOnTable} players={this.props.players}/>);
                break;
            case "score" :
                mode = (<ResultatWindow score={this.props.score}/>);
                break;
        }
        return (
            <div id="game-board">
                {mode}
            </div>
        )
    }
});

module.exports = GameBoard;