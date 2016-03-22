var React = require('react');
var Room = require('./Room.js');
var Store = require("../store/Store.js");
var GameLogic = require("../logic/GameLogic.js");

function getStateFromStore() {
    return {
        rooms: Store.getRooms()
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
        var rooms = this.state.rooms.map(function(room){
            return (<Room players={room.usersName} key={room.id}/>)
        });
        return (
            <div className="rooms-container">
                {rooms}
                <div id="add-room" onClick={this.createRoom}>Créer une partie</div>
            </div>
        )
    }
});

module.exports = RoomContainer;