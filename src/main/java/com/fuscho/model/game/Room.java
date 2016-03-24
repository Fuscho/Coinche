package com.fuscho.model.game;

import com.fuscho.model.player.Player;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by chocho on 20/03/16.
 **/
@Data
@NoArgsConstructor
public class Room {
    private Player creator;
    private List<Player> users;

    public Room(Player creator){
        this.creator = creator;
        this.users = new ArrayList<>(Arrays.asList(creator));
    }

    public void addPlayer(Player user){
        users.add(user);
    }
}
