var React = require('react');
var BiddingSpinner = require("./BiddingSpinner.js");
var GameConstant = require("../constant/GameConstant.js");
var PlayerAction = require('../logic/PlayerAction.js');

var BiddingWindow = React.createClass({

    getInitialState: function () {
        return {value: 80, suit: null};
    },

    componentDidMount: function () {
        setTimeout(function () {
            $("#bid-container").addClass("appear");
        }, 100);
    },

    onBitButtonClick: function () {
        PlayerAction.bidding({
            action: GameConstant.ACTION_BID,
            value: this.state.value,
            suit: this.state.suit
        })
    },

    onPassButtonClick: function () {
        PlayerAction.bidding({
            action: GameConstant.ACTION_PASS
        })
    },

    onSpinnerChanged: function (newValue) {
        this.setState({value: newValue});
    },

    onSuitSelected: function (e) {
        $("#suit-wanted").find("div").removeClass("selected");
        $(e.target).parent().addClass("selected");
        this.setState({suit: $(e.target).attr("data-suit")});
    },

    render: function () {
        return (
            <div id="bid-container">
                <BiddingSpinner onChange={this.onSpinnerChanged} currentBid={this.props.currentBid}/>
                <div id="suit-wanted">
                    <div onClick={this.onSuitSelected}>
                        <img src="/img/suit/hearts.png" alt="suit-hearts" data-suit="Hearts"/>
                    </div>
                    <div onClick={this.onSuitSelected}>
                        <img src="/img/suit/clubs.png" alt="suit-clubs" data-suit="Clubs"/>
                    </div>
                    <div onClick={this.onSuitSelected}>
                        <img src="/img/suit/diamonds.png" alt="suit-diamonds" data-suit="Diamonds"/>
                    </div>
                    <div onClick={this.onSuitSelected}>
                        <img src="/img/suit/spades.png" alt="suit-spade" data-suit="Spades"/>
                    </div>
                </div>
                <button id="bid-btn" onClick={this.onBitButtonClick}>Prendre</button>
                <button id="bid-btn" onClick={this.onPassButtonClick}>Passer</button>
            </div>
        )
    }
});

module.exports = BiddingWindow;