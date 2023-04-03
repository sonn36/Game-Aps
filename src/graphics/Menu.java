package graphics;

import java.awt.Color;
import java.awt.Graphics;

import main.Game;

public class Menu {


    public void tick(){

    }

    public void render(Graphics g){

        g.setColor(Color.gray);
        g.fillRect(100, 100, Game.WIDTH - 200, Game.HEIGHT - 200);

        g.setColor(Color.WHITE);
        g.fillRect(220, 180, 350, 70);
        g.setColor(Color.BLUE);
        g.drawString(String.valueOf(Game.points), 240, 220);

        g.fillRect(220, 260, 350, 70);
        g.drawString("", 0, 0);

        g.fillRect(220, 340, 350, 70);
        g.drawString("", 0, 0);
    }
    
}
