package com.fuscho.rest;

import com.fuscho.model.game.Room;
import com.fuscho.service.GameLogicService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/room")
@Slf4j
public class RoomController {

    @Autowired
    private GameLogicService gameLogicService;

    @RequestMapping(method = RequestMethod.GET, value = "/")
    public List<Room> getAllRooms() {
        return gameLogicService.getRooms();
    }

    @RequestMapping(method = RequestMethod.POST, value = "/create")
    public Integer createGame() {
        return gameLogicService.createRoom();
    }


}