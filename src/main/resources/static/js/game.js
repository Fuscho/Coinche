

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
    var card;
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
        updateCardPlayer(data);
        updateCardOnTable(data["cardsPlay"])
    });
};

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
}

var updateCardOnTable= function(cardsPlay) {
    $('#cardsContainer .card').remove();
    var player = 4;
    for (var i = cardsPlay.length - 1; i >= 0; i--) {

        $('#cardPlayJ' + player).append($('<button>', {
            class: "card " + cardsPlay[i]["suit"] + " " + cardsPlay[i]["value"]
        }));
        player--;
    }
}

