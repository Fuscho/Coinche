var React = require('react');
var PlayerAction = require('../logic/PlayerAction.js');

var pointValue = [80,90,100,110,120,130,140,150];
var BiddingSpinner = React.createClass({

    getInitialState: function() {
        return {pointCursor: 0};
    },

    getValue : function(){
        return pointValue[this.state.pointCursor];
    },

    onMoreBtnClick : function(){
        var pointCursor = this.state.pointCursor + 1;
        if(pointCursor > 7){
            pointCursor = 0;
        }
        this.setState({pointCursor: pointCursor});
        this.props.onChange(pointValue[this.state.pointCursor])
    },

    onLessBtnClick : function(){
        var pointCursor = this.state.pointCursor - 1;
        if(pointCursor < 0){
            pointCursor = 7;
        }
        this.setState({pointCursor: pointCursor});
        this.props.onChange(pointValue[this.state.pointCursor])
    },

    render : function(){
        return (
            <div id="point-wanted">
                <div className="left-spinner" onClick={this.onLessBtnClick}>-</div>
                <div className="spinner-value">{this.getValue()}</div>
                <div className="left-spinner" onClick={this.onMoreBtnClick}>+</div>
            </div>
        )
    }
});

var BiddingContainer = React.createClass({

    getInitialState: function() {
        return {value: 80, suit : null};
    },

    onBitButtonClick : function(){
        PlayerAction.bidding({
            value :  this.state.value,
            suit:  this.state.suit
        })
    },

    onSpinnerChanged : function(newValue){
        this.setState({value: newValue});
    },

    onSuitSelected : function(e){
        $("#suit-wanted").find("div").removeClass("selected");
        $(e.target).parent().addClass("selected");
        this.setState({suit: $(e.target).attr("data-suit")});
    },

    render : function(){
        return (
            <div id="bid-container">
                <BiddingSpinner onChange={this.onSpinnerChanged}/>
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
                        <img src="/img/suit/spade.png" alt="suit-spade" data-suit="Spades"/>
                    </div>
                </div>
                <button id="bid-btn" onClick={this.onBitButtonClick}>Prendre</button>
            </div>
        )
    }
});

var Card = React.createClass({
    render : function(){
        var cardStyle = "card " + this.props.card.get("suit") + " " + this.props.card.get("value");
        return (
            <div className={cardStyle} key={this.props.card.get("cardName")+this.props.card.get("value")}></div>
        )
    }
});

var CardsOnTableContainer = React.createClass({
    render : function () {
        var cardTop;
        var cardLeft;
        var cardRight;
        var cardBottom;
        this.props.cardsOnTable.forEach(function(cardOnTable){
            if(cardOnTable.get("player") == 0){
                cardBottom = (<Card card={cardOnTable.get("card")}/>);
            }
            if(cardOnTable.get("player") == 1){
                cardLeft = (<Card card={cardOnTable.get("card")}/>);
            }
            if(cardOnTable.get("player") == 2){
                cardTop = (<Card card={cardOnTable.get("card")}/>);
            }
            if(cardOnTable.get("player") == 3){
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

var GameBoard = React.createClass({
    render : function(){
        var mode = null;
        if(this.props.biddingMode){
            mode = (<BiddingContainer/>);
            console.log("Bidding");
        } else {
            mode = (<CardsOnTableContainer cardsOnTable={this.props.cardsOnTable}/>);
            console.log("Cards on table");
        }
        return (
            <div id="game-board">
                {mode}
            </div>
        )
    }
});

module.exports = GameBoard;