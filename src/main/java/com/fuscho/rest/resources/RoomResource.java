package com.fuscho.rest.resources;

import com.fuscho.model.game.Room;
import com.fuscho.model.player.Player;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Créer par mchoraine le 21/03/2016.
 */
@Data
public class RoomResource {

    private String id;
    private String creator;
    private List<String> usersName;

    public static RoomResource createResourceFromRoom(Room room, String id){
        RoomResource roomResource = new RoomResource();
        roomResource.setUsersName(room.getUsers().stream().map(Player::getName).collect(Collectors.toList()));
        roomResource.setId(id);
        roomResource.setCreator(room.getCreator().getName());
        return roomResource;
    }
}
