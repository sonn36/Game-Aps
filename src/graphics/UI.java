package graphics;

import java.awt.Color;
import java.awt.Graphics;

import main.Game;

public class UI extends Screen{

    

    public void render(Graphics g){


        g.setColor(Color.WHITE);
        g.fillRect(50, Game.HEIGHT - 50, (int)Game.player.boostTime * 2, 20);

        g.drawString(String.valueOf(Game.time), Game.WIDTH / 2,  Game.HEIGHT - 50);
        g.drawString(String.valueOf(Game.points), Game.WIDTH / 2, 10);

        g.setColor(Color.red);

 
        g.drawString("playerX: " + Game.player.getX(), 10, 10);
        g.drawString("playerY: " + Game.player.getY(), 10, 20);
        g.drawString("FPS: " + Game.fps, 10, 30);
        

    }
                                                    
}
