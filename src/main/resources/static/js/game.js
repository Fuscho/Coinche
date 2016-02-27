

$( document ).ready(function() {
    //INIT GAME
    $.ajax({
        url: "/api/init",
        method: "POST"
    })
    .done(function( data ) {
        updateCardPlayer(data);
        $.post("/api/bid", "test").done(function( data ) {
            console.log("contrat fait")
        });
    });
});


var playBtnClick = function(){
    var card;
    var conceptName = $('#cardsSelect').find(":selected").text();
    var card = {
        suit : conceptName.split(" ")[0],
        value: conceptName.split(" ")[1]
    };
    $.ajax({
        method: "POST",
        url :"/api/play",
        data : JSON.stringify(card),
        contentType : 'application/json'
    }).done(function( data ) {
        updateCardPlayer(data);
        console.log(data)
    });
};

var updateCardPlayer= function(cards){
    $('#cardsSelect option').remove();
    cards["cards"].forEach(function(card){
        if($.inArray(card, cards["playableCards"] )){
            $('#cardsSelect').append($('<option>', {
                value: card["suit"]+" "+card["value"],
                text: card["suit"]+" "+card["value"]
            }));
        }else{
            $('#cardsSelect').append($('<option disabled>', {
                value: card["suit"]+" "+card["value"],
                text: card["suit"]+" "+card["value"]
            }));
        };

    });
}