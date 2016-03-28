var React = require('react');
var GameConstant = require("../constant/GameConstant.js");

var BiddingSpinner = React.createClass({

    getInitialState: function () {
        return {pointCursor: 0};
    },

    getValue: function () {
        var pointvalue = GameConstant.POINT_VALUE[this.state.pointCursor];
        if(this.props.currentBid!=null && this.props.currentBid.get("bid") != null) {
            var bidValue = this.props.currentBid.get("bid").get("value");
            console.log("minPoint", bidValue);
            console.log("pointvalue", pointvalue);
            if (bidValue == null || pointvalue > bidValue) {
                console.log("OK")
                return pointvalue;
            } else {
                console.log("Not OK")
                this.onMoreBtnClick();
            }
        } else {
            return pointvalue;
        }
    },

    onMoreBtnClick: function () {
        var pointCursor = this.state.pointCursor + 1;
        if (pointCursor > 7) {
            pointCursor = 0;
        }
        this.setState({pointCursor: pointCursor});
        this.props.onChange(GameConstant.POINT_VALUE[pointCursor])
    },

    onLessBtnClick: function () {
        var pointCursor = this.state.pointCursor - 1;
        if (pointCursor < 0) {
            pointCursor = 7;
        }
        this.setState({pointCursor: pointCursor});
        this.props.onChange(GameConstant.POINT_VALUE[this.state.pointCursor])
    },

    render: function () {
        return (
            <div id="point-wanted">
                <div className="left-spinner" onClick={this.onLessBtnClick}>-</div>
                <div className="spinner-value">{this.getValue()}</div>
                <div className="right-spinner" onClick={this.onMoreBtnClick}>+</div>
            </div>
        )
    }
});

module.exports = BiddingSpinner;