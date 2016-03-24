var React = require('react');

var Player = React.createClass({
    render : function(){
        var player = "player-"+this.props.position;
        var bid = null;
        if(this.props.bid && this.props.bid.get("player")==this.props.player){
            bid = (
                <div className="player-bid">
                    <div className="bid-suit">{this.props.bid.get("bid").get("suit")}</div>
                    <div className="bid-value">{this.props.bid.get("bid").get("value")}</div>
                </div>
            )
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