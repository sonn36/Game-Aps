package entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import main.Game;

public class Player extends Entity {

    public double spd = 2.5;
    public boolean boost = false;
    public double boostTime = 50;
    public double maxBoostTime = 50;


    public boolean left, right, up, down;
    public String xDir = " ";
    public String yDir = "down";

    public boolean hasTrash = false;
    public Trash holdingItem;

    public Player(int x, int y, int width, int height, BufferedImage sprite) {
        super(x, y, width, height, sprite);

    }

    public void shoot() {
        if (holdingItem != null) {
            String dir = xDir + "-" + yDir;
            holdingItem.throwTrash(dir);
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
            xDir = "left";
        } else if (right && x < Game.WIDTH - 60) {
            x += spd;
            xDir = "right";
        } else {
            if (!yDir.equals(" ")) {
                xDir = " ";
            }
        }

        if (up && y > 10) {
            y -= spd;
            yDir = "up";
        } else if (down && y < Game.HEIGHT - 60) {
            y += spd;
            yDir = "down";
        } else {
            if (!xDir.equals(" ")) {
                yDir = " ";
            }
        }

    }

    public void render(Graphics g) {

        g.setColor(Color.WHITE);
        g.fillRect(getX(), getY(), width, height);
    }

}
