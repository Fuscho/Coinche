
var Game = {
    cardsOnTable : [],
    lastTrick : [],
    whoStartLastTrick : 4
};


$( document ).ready(function() {
    //INIT GAME
    $.ajax({
        url: "/api/init",
        method: "POST"
    }).done(function( data ) {
        updateCardPlayer(data);
        $.ajax({
            url : "/api/bid",
            method: 'POST',
            data : JSON.stringify({
                value : "CENT",
                suit: "Clubs"
            }),
            contentType : 'application/json'
        }).done(function( data ) {
            console.log("contrat fait")
        });
    });
});

var playCard = function(suit,value){
    var card = {
        suit : suit,
        value: value
    };
    $.ajax({
        method: "POST",
        url :"/api/play",
        data : JSON.stringify(card),
        contentType : 'application/json'
    }).done(function( data ) {
        Game.lastTrick = data["lastTrick"];
        console.log(card);
        console.log(Game.cardsOnTable);
        console.log(Game.lastTrick);
        updateCardPlayer(data);
        updateCardOnTable(data["lastTrick"], getPlayer(Game.cardsOnTable.length));
        Game.cardsOnTable = data["cardsPlay"];
    });
};

var getPlayer = function(nbCard){
    if(nbCard == 3)
        return 1;
    if(nbCard == 2)
        return 2;
    if(nbCard == 1)
        return 3;
    else
        return nbCard
}

var updateCardPlayer= function(cards){
    $('#cardsPlayerContainer .card').remove();

    cards["cards"].forEach(function(card){
        var playable = false;
        cards["playableCards"].forEach(function(playableCard) {
            if (card["suit"] == playableCard["suit"] && card["value"] == playableCard["value"]){
                playable = true;
            }
        });
        if(playable){
            $('#cardsPlayerContainer').append($('<button>', {
                class: "card " + card["suit"]+" "+card["value"],
                onclick: "playCard('"+card["suit"]+"','"+card["value"]+"')"
            }));
        }else{
            $('#cardsPlayerContainer').append($('<button>', {
                class: "card " + card["suit"]+" "+card["value"]
            }));
        }


    });
};

var updateCardOnTable= function(cardsPlay, player) {
    $('#cardsContainer .card').remove();
    for (var i = cardsPlay.length - 1; i >= 0; i--) {
        if(player == 0){
            player = 4
        }
        console.log(player);
        $('#cardPlayJ' + player).append($('<button>', {
            class: "card " + cardsPlay[i]["suit"] + " " + cardsPlay[i]["value"]
        }));
        player--;
    }
};

var nextTrick = function(){
  updateCardOnTable(Game.cardsOnTable, 4)
};