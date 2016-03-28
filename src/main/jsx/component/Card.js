var React = require('react');

var Card = React.createClass({
    render: function () {
        var cardStyle = "card " + this.props.card.get("suit") + " " + this.props.card.get("value");
        return (
            <div className={cardStyle} key={this.props.card.get("cardName")+this.props.card.get("value")}></div>
        )
    }
});

module.exports = Card;