package entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Trash extends Entity{

    public Trash(int x, int y, int width, int height, BufferedImage sprite) {
        super(x, y, width, height, sprite);

        
    }

    public void getTrash(int x, int y){

        setX(x);
        setY(y);
    }

    public void throwTrash(String dir){

        if(dir.equals("up")){
            
            this.setY(this.getY() - 50);
        }
    }


    public void render(Graphics g){
        g.setColor(Color.RED);
        g.fillRect(getX(), getY(), getWidth(), getHeight());
    }
    
}
