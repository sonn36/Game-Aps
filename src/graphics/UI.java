package graphics;

import java.awt.Color;
import java.awt.Graphics;

import main.Game;

public class UI {

    public void render(Graphics g){


        g.setColor(Color.WHITE);
        g.fillRect(50, Game.HEIGHT - 50, (int)Game.player.boostTime * 2, 20);

        g.drawString(String.valueOf(Game.time), Game.WIDTH / 2,  Game.HEIGHT - 50);
        g.drawString(String.valueOf(Game.points), Game.WIDTH / 2, 10);
    }
                                                    
}
