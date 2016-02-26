

$( document ).ready(function() {
    //INIT GAME
    $.ajax({
        url: "/api/init",
        method: "POST"
    })
    .done(function( data ) {
        data.forEach(function(card){
           $('#cardsSelect').append($('<option>', {
                value: card["suit"]+" "+card["value"],
                text: card["suit"]+" "+card["value"]
            }));
        });
        $.post("/api/bid", "test").done(function( data ) {
            console.log("contrat fait")
        });
    });
});


var playBtnClick = function(){
    var card;
    var conceptName = $('#cardsSelect').find(":selected").text();
   // card["suit"] = ...
   // card["value"] = ...
    var card = {
        suit : "Clubs",
        value: "Nine"
    };
    $.ajax({
        method: "POST",
        url :"/api/play",
        data : JSON.stringify(card),
        contentType : 'application/json'
    }).done(function( data ) {
        console.log("contrat fait")
    });
};