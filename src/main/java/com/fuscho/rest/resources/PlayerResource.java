package com.fuscho.rest.resources;

import com.fuscho.model.player.Player;
import lombok.Data;

/**
 * Créer par mchoraine le 21/03/2016.
 */
@Data
public class PlayerResource {

    private String name;

    public static PlayerResource createResourceFromPlayer(Player player){
        PlayerResource playerResource = new PlayerResource();
        playerResource.setName(player.getName());
        return playerResource;
    }
}
