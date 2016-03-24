package com.fuscho.rest;

import com.fuscho.model.game.Room;
import com.fuscho.model.player.HumanPlayer;
import com.fuscho.model.player.IAPlayer;
import com.fuscho.rest.resources.RoomResource;
import com.fuscho.service.AuthentificationService;
import com.fuscho.service.GameLogicService;
import com.fuscho.service.RoomsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/room")
@Slf4j
public class RoomController {

    @Autowired
    private RoomsService roomsService;

    @RequestMapping(method = RequestMethod.GET, value = "/")
    public List<RoomResource> getAllRooms() {
        return roomsService.getRooms();
    }

    @RequestMapping(method = RequestMethod.POST, value = "/create")
    public Integer createGame() {
        return roomsService.createRoom();
    }

    @RequestMapping(method = RequestMethod.POST, value = "/add")
    public void addIA(@RequestBody Map<String, String> form) {
        String idRoom = form.get("idRoom");
        Room room = roomsService.getRoom(idRoom);
        roomsService.addPlayerInRoom(room, idRoom, new IAPlayer("IA"+room.getUsers().size()));
    }

    @RequestMapping(method = RequestMethod.POST, value = "/join")
    public void playerJoin(@RequestBody Map<String, String> form) {
        String idRoom = form.get("idRoom");
        Room room = roomsService.getRoom(idRoom);
        roomsService.addPlayerInRoom(room, idRoom, new HumanPlayer(AuthentificationService.getAuthUser()));
    }

}