package entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class RecicleBin extends Entity{

    public String color;

    public RecicleBin(int x, int y, int width, int height, BufferedImage sprite, String color) {
        super(x, y, width, height, sprite);
        this.color = color;
    }


    public void render(Graphics g){

        if(color.equals("red")){
            g.setColor(Color.RED);
        }
        if(color.equals("green")){
            g.setColor(Color.GREEN);
        }
        if(color.equals("blue")){
            g.setColor(Color.BLUE);
        }
        if(color.equals("yellow")){
            g.setColor(Color.YELLOW);
        }
        if(color.equals("brown")){
            g.setColor(Color.CYAN);
        }
        g.fillRect(getX(), getY(), getWidth(), getHeight());
    }
    
}
