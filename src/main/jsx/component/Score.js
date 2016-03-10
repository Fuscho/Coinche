var React = require('react');

var Score = React.createClass({

    render: function () {

        return (
            <div id="score-board">
                <div className="team-name">
                    <span>Eux</span>
                    <span>Nous</span>
                </div>
                <div className="result">
                    <span>145</span>
                    <span>53</span>
                </div>
                <div className="total-result">
                    <span>145</span>
                    <span>53</span>
                </div>
            </div>
        )
    }
});

module.exports = Score;