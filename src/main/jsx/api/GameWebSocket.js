var SockJS = require('sockjs-client');
var Stomp = require('stompjs');

module.exports = {

    connectToNotification: function (GameLogic) {
        var socket = new SockJS('/notif');
        stompClient = Stomp.over(socket);
        stompClient.debug = null;
        stompClient.connect({}, function (frame) {
            //console.log('Connected: ' + frame);
            stompClient.subscribe('/topic/notifications', function (socketResponse) {
                var res = JSON.parse(socketResponse.body);
                var eventContent = JSON.parse(res.content);
                var eventType = res.headers.eventType;
                console.log(eventContent);
                switch (eventType) {
                    case "com.fuscho.model.notification.CardPlayEvent" :
                        GameLogic.showCardPlay(eventContent.cardPlay, eventContent.playerPosition);
                        break;
                    case "com.fuscho.model.notification.PlayerTurnEvent" :
                        GameLogic.updatePlayerSelectableCards(eventContent.possibleMoves);
                        break;
                    case "com.fuscho.model.notification.BidEvent" :
                        GameLogic.playerHasBidded(eventContent);
                        break;
                    case "com.fuscho.model.notification.EndRoundEvent" :
                        GameLogic.addToScore(eventContent);
                        break;
                    case "com.fuscho.model.notification.RoomCreatedEvent" :
                        GameLogic.addRoom(eventContent.room);
                        break;
                }
            });
        })
    }
};