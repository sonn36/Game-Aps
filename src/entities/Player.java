package entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import main.Game;

public class Player extends Entity {

    public double spd = 2.5;

    public boolean left, right, up, down;

    private boolean hasTrash = false;

    public Player(int x, int y, int width, int height, BufferedImage sprite) {
        super(x, y, width, height, sprite);

    }

    public void shoot(){
        if(hasTrash){
            Game.trash.throwTrash("up");
            hasTrash = false;
        }
    }

    public void tick() {

        if (checkCollision(this, Game.trash)) {

            Game.trash.getTrash(getX() + (getWidth() / 2) / 2, getY() + (getHeight() / 2) / 2);
            hasTrash = true;

        }

        if (left) {
            x -= spd;
        }
        if (right) {
            x += spd;
        }

        if (up) {
            y -= spd;
        }
        if (down) {
            y += spd;
        }

    }

    public void render(Graphics g) {

        g.setColor(Color.GREEN);
        g.fillRect(getX(), getY(), width, height);
    }

}
