var Contract = {
    suitTrump: null,
    point: null
};

var playerTurn = 0;
var endTour = false;
var cardsOnTable = [];
var playerCards = [];
var score = null;

$(document).ready(function () {

    $("#cardsContainer").hide();
    $("#next-round-btn").hide();

    connect();

    //INIT GAME
    $.ajax({
        url: "/api/init",
        method: "POST"
    }).done(function (data) {
        playerCards = data;
        updateCardPlayer(data, []);
    });

    $("#bitContainer").find(".suitWanted button").click(function () {
        var suit = $(this).text();
        Contract.suitTrump = suit;
        $("#bitContainer").find(".suitWanted button").removeClass("selected");
        $(this).addClass("selected");
    });

    $("#bitContainer .contract button").click(function () {
        var point = $(this).text();
        Contract.point = point;
        $("#bitContainer .contract button").removeClass("selected");
        $(this).addClass("selected");
    });
});

var connect = function () {
    var socket = new SockJS('/notif');
    stompClient = Stomp.over(socket);
    stompClient.debug = null;
    stompClient.connect({}, function (frame) {
        //console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/notifications', function (calResult) {
            var event = JSON.parse(calResult.body);
            var eventContent = JSON.parse(event.content);
            switch (event.headers.eventType) {
                case "com.fuscho.model.notification.CardPlayEvent" :
                    if (!endTour) {
                        updateCardOnTable(eventContent.cardPlay, eventContent.playerPosition);
                    }
                    cardsOnTable.push(eventContent);
                    if (cardsOnTable.length == 4) {
                        cardsOnTable = [];
                        endTour = true;
                    }
                    break;
                case "com.fuscho.model.notification.PlayerTurnEvent" :
                    console.log(eventContent, playerCards);
                    updateCardPlayer(playerCards, eventContent.possibleMoves);
                    break;
                case "com.fuscho.model.notification.EndRoundEvent" :
                    score = eventContent.score;
                    break;
            }
        });
    });
};

var playCard = function (suit, value) {
    console.log(suit, value);
    console.log(playerCards);
    playerCards = playerCards.filter(function (playerCard) {
        return !(playerCard.suit == suit && playerCard.value == value);
    });
    console.log(playerCards);
    updateCardPlayer(playerCards, []);
    var card = {
        suit: suit,
        value: value
    };
    $.ajax({
        method: "POST",
        url: "/api/play",
        data: JSON.stringify(card),
        contentType: 'application/json'
    }).done(function (data) {
        playerCards = data;
    });
};

var updateCardPlayer = function (cards, playableCards) {
    $('#cardsPlayerContainer').find('.card').remove();
    cards.forEach(function (card) {
        var playable = false;
        playableCards.forEach(function (playableCard) {
            if (card["suit"] == playableCard["suit"] && card["value"] == playableCard["value"]) {
                playable = true;
            }
        });
        if (playable) {
            $('#cardsPlayerContainer').append($('<button>', {
                class: "card " + card["suit"] + " " + card["value"],
                onclick: "playCard('" + card["suit"] + "','" + card["value"] + "')"
            }));
        } else {
            $('#cardsPlayerContainer').append($('<button>', {
                class: "card " + card["suit"] + " " + card["value"]
            }));
        }
    });
};

var updateCardOnTable = function (cardPlay, player) {
    $('#cardPlayJ' + parseInt(player + 1)).append($('<button>', {
        class: "card " + cardPlay["suit"] + " " + cardPlay["value"]
    }));
};

var nextTrick = function () {
    endTour = false;
    $('#cardsContainer').find('.card').remove();
    if (score) {
        alert(score);
        $("#next-round-btn").show();
    } else {
        cardsOnTable.forEach(function (cardPlayed) {
            updateCardOnTable(cardPlayed.cardPlay, cardPlayed.playerPosition)
        });
    }
};

var nextRound = function () {
    cardsOnTable = [];
    $('#cardsContainer').find('.card').remove();
    $.ajax({
        url: "/api/next-round",
        method: "POST"
    }).done(function (data) {
        $("#cardsContainer").hide();
        $("#next-round-btn").hide();
        $("#bitContainer").show();
        score = null;
        playerCards = data;
        updateCardPlayer(data, []);
    });
};

var bit = function () {
    if (Contract.point != null && Contract.suitTrump != null) {
        $("#cardsContainer").show();
        $("#bitContainer").hide();
        $.ajax({
            url: "/api/bid",
            method: 'POST',
            data: JSON.stringify({
                value: Contract.point,
                suit: Contract.suitTrump
            }),
            contentType: 'application/json'
        }).done(function (data) {
            $("#currentBid").find(".bidder").text(data.contractBidder);
            $("#currentBid").find(".suit").text(data.contractSuit);
            $("#currentBid").find(".point").text(data.contractPoint);
        });
    } else {
        alert("Erreur : Séléctionner un atout et une mise")
    }
};
