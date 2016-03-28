var React = require('react');

var Cards = React.createClass({
    render: function () {
        var allCards = this.props.cards;
        var selectableCards = this.props.selectableCards;
        var onCardClick = this.props.onCardClick;

        var cards = allCards.map(function (card, index) {
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
                <div className={cardStyle} key={card.get("cardName")+card.get("value")} style={style} onClick={onCardClick} data-card={index}></div>
            )
        });
        return (
            <div id="current-player-cards">
                {cards}
            </div>
        );
    }
});

module.exports = Cards;