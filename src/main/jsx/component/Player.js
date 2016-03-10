var React = require('react');

var Player = React.createClass({
    render : function(){
        var player = "player-"+this.props.position;
        return (
            <div id={player}>
                <div className="profil">
                    <div className="avatar"></div>
                    <div className="player-name">IA</div>
                </div>
            </div>
        )
    }
});

module.exports = Player;