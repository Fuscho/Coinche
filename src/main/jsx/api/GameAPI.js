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
        });
    },

    addIAinRoom : function(idRoom){
        $.ajax({
            url: "/api/room/add",
            method: "POST",
            contentType: 'application/json',
            data: JSON.stringify({
                idRoom: idRoom
            })
        })
    },

    userJoinRoom : function(idRoom) {
        $.ajax({
            url: "/api/room/join",
            method: "POST",
            contentType: 'application/json',
            data: JSON.stringify({
                idRoom: idRoom
            })
        })
    },

    playerBidding: function(gameID, playerBid, GameLogicCallback){
        $.ajax({
            url: "/api/bid/"+gameID,
            method: 'POST',
            data: JSON.stringify({
                action : playerBid.action,
                value: playerBid.value,
                suit: playerBid.suit
            }),
            contentType: 'application/json'
        }).success(function(){
            GameLogicCallback()
        })
    },

    playCard : function(gameID, cardPlayed, GameLogicCallback){
        $.ajax({
            url: "/api/play/"+gameID,
            method: 'POST',
            data: JSON.stringify({
                value: cardPlayed.get("value"),
                suit: cardPlayed.get("suit")
            }),
            contentType: 'application/json'
        }).success(function(response){
            GameLogicCallback(response);
        })
    },

    nextRound : function(GameLogicCallback){
        $.ajax({
            url: "/api/next-round",
            method: 'POST',
            contentType: 'application/json'
        }).success(function(response){
            GameLogicCallback(response);
        })
    }
};