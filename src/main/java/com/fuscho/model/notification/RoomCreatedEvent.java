package com.fuscho.model.notification;

import com.fuscho.model.game.Room;
import com.fuscho.rest.resources.RoomResource;
import lombok.Getter;

/**
 * Créer par mchoraine le 05/03/2016.
 */
public class RoomCreatedEvent implements Event {

    @Getter
    private RoomResource room;

    public RoomCreatedEvent(Room room, String id) {
        this.room = RoomResource.createResourceFromRoom(room, id);
    }
}
