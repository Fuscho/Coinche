var React = require('react');
var PlayerAction = require('../logic/PlayerAction.js');

var CurrentPlayer = React.createClass({

    onCardClick : function (e) {
        if($(e.target).hasClass("selectable")){
            var cardSelectedIndex = $(e.target).attr("data-card");
            PlayerAction.playCard(this.props.cards.get(cardSelectedIndex));
        }
    },

    render: function () {
        var cards = null;
        var selectableCards = this.props.selectableCards;
        var player = this;
        if (this.props.cards != null) {
            cards = this.props.cards.map(function (card, index) {
                var cardStyle = "card " + card.get("suit") + " " + card.get("value");
                if(selectableCards.indexOf(card) != -1){
                    cardStyle = cardStyle+" selectable";
                }
                return (
                    <div className={cardStyle} key={card.get("cardName")+card.get("value")} onClick={player.onCardClick} data-card={index}></div>
                )
            });
        }

        return (
            <div id="current-player">
                <div id="current-player-cards">
                    {cards}
                </div>
                <div className="player-board" onClick={PlayerAction.goNextTurn}></div>
            </div>
        )
    }
});

module.exports = CurrentPlayer;