var SockJS = require('sockjs-client');
var Stomp = require('stompjs');

module.exports = {

    connectToNotification: function (currentUser, GameLogic) {
        var socket = new SockJS('/notif');
        stompClient = Stomp.over(socket);
        stompClient.debug = null;
        stompClient.connect(currentUser.pseudo, function (frame) {
            //console.log('Connected: ' + frame);
            var wsCallback = function (socketResponse) {
                var res = JSON.parse(socketResponse.body);
                var eventContent = JSON.parse(res.content);
                var eventType = res.headers.eventType;
                console.log(eventType, eventContent);
                switch (eventType) {
                    case "com.fuscho.model.notification.RoomCreatedEvent" :
                        GameLogic.addRoom(eventContent.room);
                        break;
                    case "com.fuscho.model.notification.PlayerJoinRoomEvent" :
                        GameLogic.updateRoom(eventContent.room);
                        break;
                    case "com.fuscho.model.notification.GameStartedEvent" :
                        GameLogic.setGameStarted(eventContent, true);
                        break;
                    case "com.fuscho.model.notification.PlayerCardsEvent" :
                        GameLogic.onGameInitilized(eventContent.cards);
                        break;
                    case "com.fuscho.model.notification.PlayerBidTurnEvent" :
                        GameLogic.playerHasToBid();
                        break
                    case "com.fuscho.model.notification.CardPlayEvent" :
                        GameLogic.showCardPlay(eventContent.cardPlay, eventContent.player);
                        break;
                    case "com.fuscho.model.notification.PlayerTurnEvent" :
                        GameLogic.updatePlayerSelectableCards(eventContent.possibleMoves);
                        break;
                    case "com.fuscho.model.notification.BidEvent" :
                        GameLogic.playerHasBidded(eventContent);
                        break;
                    case "com.fuscho.model.notification.RoundResultEvent" :
                        setTimeout(function () {
                            GameLogic.addToScore(eventContent);
                        }, 1000);
                        break;
                }
            };
            stompClient.subscribe('/topic/notifications', wsCallback);
            stompClient.subscribe('/user/queue/notifications', wsCallback);
        })
    }
};