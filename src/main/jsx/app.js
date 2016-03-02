var React = require('react');
var ReactDOM = require('react-dom');

var App = React.createClass({
    render : function(){
        return (
            <h1>Hello, world!</h1>
        )
    }
});

module.exports = {
    React: React,
    ReactDOM: ReactDOM,
    App : App
};
