var React = require('react');
var PlayerAction = require("../logic/PlayerAction.js");

var Room = React.createClass({

    addIAinRoom: function (idRoom) {
        PlayerAction.addIAinRoom(idRoom);
    },

    userJoinRoom : function(idRoom) {
        PlayerAction.userJoinRoom(idRoom);
    },

    render: function () {
        var $this = this;
        var playerInRoom = this.props.players.map(function (player, index) {
            return (
                <div className="player-in-room" key={$this.props.idRoom+"-"+player+"-"+index}>
                    {player}
                </div>
            )
        });
        for (var i = playerInRoom.length; i < 4; i++) {
            if (this.props.creator != window.user.pseudo) {
                var joinRoom = function () {
                    $this.userJoinRoom($this.props.idRoom);
                };
                playerInRoom.push(
                    <div className="player-in-room" key={$this.props.idRoom+"-"+i} onClick={joinRoom}>
                        Rejoindre
                    </div>
                )
            } else {
                var addIA = function () {
                    $this.addIAinRoom($this.props.idRoom);
                };
                playerInRoom.push(
                    <div className="player-in-room" key={$this.props.idRoom+"-"+i} onClick={addIA}>
                        Add IA
                    </div>
                )
            }
        }
        return (
            <div className="room">
                {playerInRoom}
            </div>
        )
    }
});

module.exports = Room;