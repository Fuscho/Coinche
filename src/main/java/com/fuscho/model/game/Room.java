package com.fuscho.model.game;

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
    private List<String> users = new ArrayList<>();

    public void addUser(String user){
        users.add(user);
    }
}
