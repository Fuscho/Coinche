var React = require('react');

var ResultatWindow = React.createClass({

    componentDidMount: function () {
        setTimeout(function () {
            $("#resultat-container").addClass("appear");
        }, 100);
    },

    render: function () {
        var score = this.props.score.get(this.props.score.count() - 1);
        var resultText;
        if(score.get("success")){
            resultText = "Contrat rempli !";
        } else {
            resultText = "Contrat échoué !";
        }
        return (
            <div id="resultat-container">
                <p>
                    {resultText}<br/>
                    Votre équipe marque <br/>
                    <span className="points">{score.get("you")}</span> pts <br/>
                    Vos adversaires marquent {score.get("other")} pts
                </p>
            </div>
        )
    }

});

module.exports = ResultatWindow;