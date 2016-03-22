package com.fuscho.model.game;

import com.fuscho.model.User;
import com.fuscho.model.player.Player;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chocho on 20/03/16.
 **/
@Data
@NoArgsConstructor
public class Room {
    private List<Player> users = new ArrayList<>();

    public void addPlayer(Player user){
        users.add(user);
    }
}
