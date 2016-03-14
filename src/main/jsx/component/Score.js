var React = require('react');

var Score = React.createClass({

    render: function () {
        var score = null;
        console.log(this.props.score);
        if(this.props.score){
            score = this.props.score.map(function(item){
                return (<div className="result">
                    <span>{item.get("you")}</span>
                    <span>{item.get("other")}</span>
                </div>)
            })
        }
        return (
            <div id="score-board">
                <div className="team-name">
                    <span>Nous</span>
                    <span>Eux</span>
                </div>
                {score}
                <div className="total-result">
                    <span>0 / 1000</span>
                    <span>0 / 1000</span>
                </div>
            </div>
        )
    }
});

module.exports = Score;