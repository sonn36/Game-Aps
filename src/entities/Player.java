package entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import main.Game;

public class Player extends Entity {

    public double spd = 2.5;
    public boolean boost = false;
    public double boostTime = 50;
    public double maxBoostTime = 50;


    public boolean left, right, up, down;

    public boolean hasTrash = false;
    public Trash holdingItem;

    public Player(int x, int y, int width, int height, BufferedImage sprite) {
        super(x, y, width, height, sprite);

    }

    public void shoot(MouseEvent e) {
        if (holdingItem != null) {
            int xx = e.getX();
            int yy = e.getY();

            holdingItem.throwTrash(xx, yy);
            holdingItem = null;
        }
    }


    public void tick() {

        spd = 2.5;
        if(boost){
            if(boostTime > 0){
                spd = 5.0;
                boostTime --;
            }
        }
        else{
            if(boostTime < maxBoostTime){
                boostTime+= 0.2;
            }
        }

        for (int x = 0; x < Game.entities.size(); x++) {
            Entity e = Game.entities.get(x);
            if (e instanceof Trash) {
                
                if (checkCollision(this, e)) {

                    break;
                }

                if(x == Game.entities.size() - 1){
                    holdingItem = null;
                }
            }
        }

        if (left && x > 10) {
            x -= spd;
        } 
        if (right && x < Game.WIDTH - 60) {
            x += spd;
        } 

        if (up && y > 10) {
            y -= spd;
        }
        if (down && y < Game.HEIGHT - 60) {
            y += spd;
        } 

    }

    public void render(Graphics g) {

        g.setColor(Color.WHITE);
        g.fillRect(getX(), getY(), width, height);
    }

}
