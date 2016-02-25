package com.fuscho;

import com.fuscho.model.game.Game;
import com.fuscho.model.player.Player;
import org.junit.Test;

/**
 * Créer par mchoraine le 25/02/2016.
 */
public class GameTest {

    @Test
    public void shouldPlay(){
        Game game = new Game();
        game.addPlayer(new Player());
        game.addPlayer(new Player());
        game.addPlayer(new Player());
        game.addPlayer(new Player());
        game.launchGame();
        game.getClass();
    }
}
