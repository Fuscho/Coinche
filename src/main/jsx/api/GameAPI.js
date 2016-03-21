module.exports =  {

    getRooms : function(GameCallback){
        $.ajax({
            url: "/api/room/",
            method: "GET"
        }).done(function (data) {
            GameCallback(data);
        });
    },

    createRoom : function(){
        $.ajax({
            url: "/api/room/create",
            method: "POST"
        }).done(function (data) {
            //GameCallback(data);
        });
    },

    initGame : function(GameCallback){
        $.ajax({
            url: "/api/init",
            method: "POST"
        }).done(function (data) {
            GameCallback(data);
        });
    },

    playerBidding: function(playerBid, GameLogicCallback){
        $.ajax({
            url: "/api/bid",
            method: 'POST',
            data: JSON.stringify({
                value: playerBid.value,
                suit: playerBid.suit
            }),
            contentType: 'application/json'
        }).success(function(){
            GameLogicCallback()
        })
    },

    playCard : function(cardPlayed, GameLogicCallback){
        $.ajax({
            url: "/api/play",
            method: 'POST',
            data: JSON.stringify({
                value: cardPlayed.get("value"),
                suit: cardPlayed.get("suit")
            }),
            contentType: 'application/json'
        }).success(function(response){
            GameLogicCallback(response);
        })
    }
};