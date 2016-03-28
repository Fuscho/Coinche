var React = require('react');

var CurrentBid = React.createClass({
    render: function () {
        var suitUrlImg = "/img/suit/"+this.props.bid.get("bid").get("suit").toLowerCase()+".png";
        return (
            <div className="player-bid">
                <div className="bid-suit"><img src={suitUrlImg} alt={this.props.bid.get("bid").get("suit")}/></div>
                <div className="bid-value">{this.props.bid.get("bid").get("value")}</div>
            </div>
        )
    }
});

module.exports = CurrentBid;