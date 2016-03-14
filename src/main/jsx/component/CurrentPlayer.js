var React = require('react');
var PlayerAction = require('../logic/PlayerAction.js');

function animateAppendTo(el, where, float){
    var pos0 = el.offset();
    el.appendTo(where);
    var pos1 = el.offset();
    el.clone().appendTo(float ? float : 'body');
    float.css({
        'position': 'absolute',
        'left': pos0.left,
        'top': pos0.top,
        'zIndex': 1000
    });
    el.hide();
    float.animate(
        {'top': pos1.top,'left': pos1.left},
        'slow',
        function(){
            el.show();
            float.remove();
        });
}

var CurrentPlayer = React.createClass({

    onCardClick : function (e) {
        if($(e.target).hasClass("selectable")){
            //animateAppendTo($(e.target), $("#card-player-bottom"), $(e.target));
            var cardSelectedIndex = $(e.target).attr("data-card");
            PlayerAction.playCard(this.props.cards.get(cardSelectedIndex));
        }
    },

    shouldComponentUpdate : function(nextProps){
        return nextProps.cards==this.props.cards;
    },

    render: function () {
        var cards = null;
        var allCards = this.props.cards;
        var selectableCards = this.props.selectableCards;
        var player = this;
        var bid = null;
        if (this.props.cards != null) {
            cards = this.props.cards.map(function (card, index) {
                var cardStyle = "card " + card.get("suit") + " " + card.get("value");
                if(selectableCards.indexOf(card) != -1){
                    cardStyle = cardStyle+" selectable";
                }
                var rotationDeg = index-(allCards.count()/2);
                var marginLeft = (index+1)*35-((allCards.count()/2))*40  - 37.5;
                var style = {
                    "transform": "rotate("+rotationDeg+"deg)",
                    "transformOrigin": "100% 50%",
                    "marginLeft" : marginLeft+"px"
                };
                return (
                    <div className={cardStyle} key={card.get("cardName")+card.get("value")} style={style} onClick={player.onCardClick} data-card={index}></div>
                )
            });
        }

        if(this.props.bid && parseInt(this.props.bid.get("player"))==parseInt(this.props.player)){
            var suitUrlImg = "/img/suit/"+this.props.bid.get("bid").get("suit").toLowerCase()+".png",
            bid = (
                <div className="player-bid">
                    <div className="bid-suit"><img src={suitUrlImg} alt={this.props.bid.get("bid").get("suit")}/></div>
                    <div className="bid-value">{this.props.bid.get("bid").get("value")}</div>
                </div>
            )
        }

        return (
            <div id="current-player">
                <div id="current-player-cards">
                    {cards}
                </div>
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