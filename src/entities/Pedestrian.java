package entities;

import java.awt.image.BufferedImage;
import java.util.Random;

import main.Game;

public class Pedestrian  extends Entity{

    private int pos;

    public Pedestrian(int x, int y, int width, int height, BufferedImage sprite, int pos) {
        super(x, y, width, height, sprite);
        this.pos = pos;
    }
    

    public void tick(){

        Random rand = new Random();

        int i =rand.nextInt(0, 4);

        String[] colors = {"red", "green", "blue", "yellow", "brown"};

        Trash trash = new Trash(getX(), getY(), 25, 25, null, colors[i]);
        Game.entities.add(trash);
        if(pos == 0){

            trash.throwTrash(" -right");
        }
        if(pos == 1){
            trash.throwTrash(" -left");
        }

        Game.entities.remove(this);
    }
}