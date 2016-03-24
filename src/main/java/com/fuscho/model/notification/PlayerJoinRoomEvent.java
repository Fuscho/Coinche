package com.fuscho.model.notification;

import com.fuscho.model.game.Room;
import com.fuscho.rest.resources.RoomResource;
import lombok.Data;

/**
 * Créer par mchoraine le 22/03/2016.
 */
@Data
public class PlayerJoinRoomEvent implements Event {

    private RoomResource room;

    public PlayerJoinRoomEvent(Room room, String idRoom) {
        this.room = RoomResource.createResourceFromRoom(room, idRoom);
    }
}
