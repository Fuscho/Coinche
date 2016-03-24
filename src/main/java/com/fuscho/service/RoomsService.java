package com.fuscho.service;

import com.fuscho.model.game.Room;
import com.fuscho.model.notification.GameStartedEvent;
import com.fuscho.model.notification.PlayerJoinRoomEvent;
import com.fuscho.model.notification.RoomCreatedEvent;
import com.fuscho.model.player.HumanPlayer;
import com.fuscho.model.player.Player;
import com.fuscho.rest.resources.RoomResource;
import com.fuscho.websocket.Message;
import com.fuscho.websocket.MessageBuilder;
import com.fuscho.websocket.StompMessagingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Créer par mchoraine le 05/03/2016.
 */
@Service
@Slf4j
public class RoomsService {

    @Autowired
    private StompMessagingService messagingService;

    @Autowired
    private GameLogicService gameLogicService;

    private Map<String,Room> rooms = new HashMap<>();

    public Integer createRoom() {
        Room e = new Room(new HumanPlayer(AuthentificationService.getAuthUser()));
        String id = UUID.randomUUID().toString();
        rooms.put(id,e);
        messagingService.send(MessageBuilder.message(new RoomCreatedEvent(e, id)));
        return rooms.size() - 1;
    }

    public List<RoomResource> getRooms() {
        return rooms.entrySet().stream().map(room -> RoomResource.createResourceFromRoom(room.getValue(), room.getKey())).collect(Collectors.toList());
    }

    public Room getRoom(String idRoom){
        Room room = rooms.get(idRoom);
        if(room != null){
            return room;
        } else {
            return null;
        }
    }

    public void addPlayerInRoom(Room currentRoom, String idRoom, Player player) {
        if(currentRoom != null){
            currentRoom.addPlayer(player);
            messagingService.send(MessageBuilder.message(new PlayerJoinRoomEvent(currentRoom, idRoom)));
            if(currentRoom.getUsers().size() == 4){
                gameLogicService.startGame(currentRoom.getUsers(), idRoom);
                rooms.remove(idRoom);
            }
        }
    }

}
