var React = require('react');
//Components
var CurrentBid = require("./CurrentBid.js");
var Cards = require("./Cards.js");
//Actions
var PlayerAction = require('../logic/PlayerAction.js');

var CurrentPlayer = React.createClass({

    onCardClick : function (e) {
        console.log(e);
        if($(e.target).hasClass("selectable")){
            var cardSelectedIndex = $(e.target).attr("data-card");
            PlayerAction.playCard(this.props.cards.get(cardSelectedIndex));
        }
    },

    shouldComponentUpdate : function(nextProps){
        return nextProps.cards!=this.props.cards || nextProps.bid!=this.props.bid || nextProps.selectableCards != this.props.selectableCards;
    },

    render: function () {
        var bid = null;
        if(this.props.bid && this.props.bid.get("player")==this.props.player.get("name")){
            bid = (<CurrentBid bid={this.props.bid} />)
        }
        return (
            <div id="current-player">
                <Cards onCardClick={this.onCardClick} cards={this.props.cards} selectableCards={this.props.selectableCards} />
                <div className="player-board" onClick={PlayerAction.goNextTurn}></div>
                <div className="profil">
                    <div className="avatar"></div>
                </div>
                {bid}
            </div>
        )
    }
});

module.exports = CurrentPlayer;