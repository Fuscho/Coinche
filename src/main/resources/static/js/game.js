var Game = {
    cardsOnTable: [],
    lastTrick: [],
    score: null
};

var Contract = {
    suitTrump: null,
    point: null
};


$(document).ready(function () {

    $("#cardsContainer").hide();

    //INIT GAME
    $.ajax({
        url: "/api/init",
        method: "POST"
    }).done(function (data) {
        updateCardPlayer(data);
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

var playCard = function (suit, value) {
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
        Game.lastTrick = data["lastTrick"];
        updateCardPlayer(data);
        updateCardOnTable(data["lastTrick"], getPlayer(Game.cardsOnTable.length));
        Game.cardsOnTable = data["cardsPlay"];
        Game.score = data["score"];
    });
};

var getPlayer = function (nbCard) {
    if (nbCard == 3)
        return 1;
    if (nbCard == 2)
        return 2;
    if (nbCard == 1)
        return 3;
    else
        return nbCard
};

var updateCardPlayer = function (cards) {
    $('#cardsPlayerContainer').find('.card').remove();

    cards["cards"].forEach(function (card) {
        var playable = false;
        cards["playableCards"].forEach(function (playableCard) {
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

var updateCardOnTable = function (cardsPlay, player) {
    $('#cardsContainer').find('.card').remove();
    for (var i = cardsPlay.length - 1; i >= 0; i--) {
        if (player == 0) {
            player = 4
        }
        $('#cardPlayJ' + player).append($('<button>', {
            class: "card " + cardsPlay[i]["suit"] + " " + cardsPlay[i]["value"]
        }));
        player--;
    }
};

var nextTrick = function () {
    if(Game.score){
        alert(Game.score);
    } else {
        updateCardOnTable(Game.cardsOnTable, 4)
    }
};

var bit = function () {
    if(Contract.point != null && Contract.suitTrump != null) {
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
            console.log("contrat fait")
        });
    } else {
        alert("Erreur : Séléctionner un atout et une mise")
    }
};
