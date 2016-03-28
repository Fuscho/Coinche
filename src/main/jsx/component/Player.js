var React = require('react');
var CurrentBid = require("./CurrentBid.js");

var Player = React.createClass({
    render: function () {
        var player = "player-" + this.props.position;
        var bid = null;
        if (this.props.bid && this.props.bid.get("player") == this.props.player.get("name")) {
            bid = (<CurrentBid bid={this.props.bid} />)
        }
        return (
            <div id={player}>
                <div className="profil">
                    <div className="avatar"></div>
                    <div className="player-name">{this.props.player}</div>
                </div>
                {bid}
            </div>
        )
    }
});

module.exports = Player;