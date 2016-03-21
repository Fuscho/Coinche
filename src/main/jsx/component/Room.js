var React = require('react');

var Room = React.createClass({
    render : function(){
        return (
            <div className="room">
                <div className="player-in-room">
                    Human
                </div>
                <div className="player-in-room">
                    IA
                </div>
                <div className="player-in-room">
                    IA
                </div>
                <div className="player-in-room">
                    IA
                </div>
            </div>
        )
    }
});

module.exports = Room;