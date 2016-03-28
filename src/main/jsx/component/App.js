var React = require('react');
var Room = require('./Room.js');
var Store = require("../store/Store.js");
var GameLogic = require("../logic/GameLogic.js");
var Player = require('./Player.js');
var CurrentPlayer = require('./CurrentPlayer.js');
var GameBoard = require('./GameBoard.js');
var Score = require('./Score.js');

function getStateFromStore() {
    return {
        user : Store.getUser(),
        rooms: Store.getRooms(),
        isGameStarted : Store.isGameStarted(),
        players : Store.getGame(),
        playerCards: Store.getCurrentPlayerCards(),
        playerSelectableCards : Store.getCurrentPlayerSelectableCard(),
        bid : Store.getBid(),
        mode : Store.getMode(),
        cardsOnTable : Store.getCardsOnTable(),
        score : Store.getScore()
    }
}

var RoomContainer = React.createClass({

    getInitialState: function() {
        return getStateFromStore();
    },

    _onStateChange: function() {
        this.setState(getStateFromStore());
    },

    componentDidMount: function() {
        Store.addStateChangeListener(this._onStateChange);
    },

    componentWillMount(){
        GameLogic.getRooms();
    },

    componentWillUnmount: function() {
        Store.removeStateChangeListener(this._onStateChange);
    },

    createRoom : function(){
        GameLogic.createRoom();
    },

    render : function(){
        if(this.state.isGameStarted){
            return (
                <div className="game-container">
                    <Player position="top" bid={this.state.bid} player={this.state.players.get("topPlayer")}/>
                    <Player position="left" bid={this.state.bid} player={this.state.players.get("leftPlayer")}/>
                    <Player position="right" bid={this.state.bid} player={this.state.players.get("rightPlayer")}/>
                    <CurrentPlayer cards={this.state.playerCards} selectableCards={this.state.playerSelectableCards} bid={this.state.bid} bid={this.state.bid} player={this.state.players.get("currentPlayer")}/>
                    <GameBoard mode={this.state.mode} cardsOnTable={this.state.cardsOnTable} score={this.state.score} players={this.state.players} currentBid={this.state.bid}/>
                    <Score score={this.state.score}/>
                </div>
            )
        } else {
            var rooms = this.state.rooms.map(function (room) {
                return (<Room players={room.usersName} idRoom={room.id} creator={room.creator}/>)
            });
            return (
                <div className="rooms-container">
                    {rooms}
                    <div id="add-room" onClick={this.createRoom}>Créer une partie</div>
                </div>
            )
        }
    }
});

module.exports = RoomContainer;