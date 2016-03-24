var React = require('react');
var TransitionMotion = require('react-motion').TransitionMotion;
var spring = require('react-motion').spring;
var PlayerAction = require('../logic/PlayerAction.js');

var pointValue = [80,90,100,110,120,130,140,150];
var ACTION_PASS = "PASS";
var ACTION_BID = "BID";
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
        this.props.onChange(pointValue[pointCursor])
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
                <div className="right-spinner" onClick={this.onMoreBtnClick}>+</div>
            </div>
        )
    }
});

var BiddingContainer = React.createClass({

    getInitialState: function() {
        return {value: 80, suit : null};
    },

    componentDidMount : function(){
        setTimeout(function(){
            $("#bid-container").addClass("appear");
        }, 100);
    },

    onBitButtonClick : function(){
        PlayerAction.bidding({
            action :  ACTION_BID,
            value :  this.state.value,
            suit:  this.state.suit
        })
    },

    onPassButtonClick : function(){
        PlayerAction.bidding({
            action :  ACTION_PASS
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
                        <img src="/img/suit/spades.png" alt="suit-spade" data-suit="Spades"/>
                    </div>
                </div>
                <button id="bid-btn" onClick={this.onBitButtonClick}>Prendre</button>
                <button id="bid-btn" onClick={this.onPassButtonClick}>Passer</button>
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
        var players = this.props.players;
        console.log("players", players);
        console.log("cards", this.props.cardsOnTable);
        this.props.cardsOnTable.forEach(function(cardOnTable){
            if(cardOnTable.get("player") == players.get("currentPlayer").get("name")){
                cardBottom = (<Card card={cardOnTable.get("card")}/>);
            }
            if(cardOnTable.get("player") == players.get("leftPlayer").get("name")){
                cardLeft = (<Card card={cardOnTable.get("card")}/>);
            }
            if(cardOnTable.get("player") == players.get("topPlayer").get("name")){
                cardTop = (<Card card={cardOnTable.get("card")}/>);
            }
            if(cardOnTable.get("player") == players.get("rightPlayer").get("name")){
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

var ResultatContainer = React.createClass({

    componentDidMount : function(){
        setTimeout(function(){
            $("#resultat-container").addClass("appear");
        }, 100);
    },

    render : function(){
       var score = this.props.score.get(this.props.score.count() - 1);
       return (
           <div id="resultat-container">
               <p>
                   Contrat rempli !<br/>
                   Votre équipe marque <br/>
                   <span className="points">{score.get("you")}</span> pts <br/>
                   Vos adversaires marquent {score.get("other")} pts
               </p>
           </div>
       )
   }

});
var GameBoard = React.createClass({
    render : function(){
        var mode = null;
        switch(this.props.mode) {
            case "bidding" :
                mode = (<BiddingContainer/>);
                break;
            case "playing" :
                mode = (<CardsOnTableContainer cardsOnTable={this.props.cardsOnTable} players={this.props.players}/>);
                break;
            case "score" :
                mode = (<ResultatContainer score={this.props.score}/>);
                break;
        }
        return (
            <div id="game-board">
                {mode}
            </div>
        )
    }
});

module.exports = GameBoard;