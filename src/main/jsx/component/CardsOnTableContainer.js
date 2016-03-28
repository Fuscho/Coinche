var React = require('react');
var Card = require("./Card.js");

var CardsOnTableContainer = React.createClass({
    render: function () {
        var cardTop;
        var cardLeft;
        var cardRight;
        var cardBottom;
        var players = this.props.players;
        this.props.cardsOnTable.forEach(function (cardOnTable) {
            if (cardOnTable.get("player") == players.get("currentPlayer").get("name")) {
                cardBottom = (<Card card={cardOnTable.get("card")}/>);
            }
            if (cardOnTable.get("player") == players.get("leftPlayer").get("name")) {
                cardLeft = (<Card card={cardOnTable.get("card")}/>);
            }
            if (cardOnTable.get("player") == players.get("topPlayer").get("name")) {
                cardTop = (<Card card={cardOnTable.get("card")}/>);
            }
            if (cardOnTable.get("player") == players.get("rightPlayer").get("name")) {
                cardRight = (<Card card={cardOnTable.get("card")}/>);
            }
        });
        return (
            <div id="cards-on-table-container">
                <div id="card-player-top">
                    {cardTop}
                </div>
                <div id="card-player-left">
                    {cardLeft}
                </div>
                <div id="card-player-right">
                    {cardRight}
                </div>
                <div id="card-player-bottom">
                    {cardBottom}
                </div>
            </div>
        )
    }
});

module.exports = CardsOnTableContainer;