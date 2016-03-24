var React = require('react');
var ReactDOM = require('react-dom');
var App = require('./component/App.js');
var GameLogic = require('./logic/GameLogic.js');

GameLogic.initGame();

module.exports = {
    React: React,
    ReactDOM: ReactDOM,
    App : App
};
