

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
    });
    //BID
    var value = {
        "value": "fdp"
    }
    $.ajax({
        url: "/api/bid",
        method: "POST",
        dataType: "json",
        data: value
    })
    .done(function( data ) {
        console.log("contrat fait")
    });
});


var playBtnClick = function(){
    var card;
    var conceptName = $('#cardsSelect').find(":selected").text();
    console.log(conceptName);
    conceptName.split()
   // card["suit"] = ...
   // card["value"] = ...

}