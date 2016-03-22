var React = require('react');

var Room = React.createClass({
    render : function(){
        var playerInRoom = this.props.players.map(function(player){
            return (
                <div className="player-in-room" key={player}>
                    {player}
                </div>
            )
        });
        for (var i = 4 - playerInRoom.length; i--; i < 0){
            playerInRoom.push(
                <div className="player-in-room" key={i} onClick={}>
                   Add IA
                </div>
            )
        }
        return (
            <div className="room">
                {playerInRoom}
            </div>
        )
    }
});

module.exports = Room;