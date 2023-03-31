package entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import main.Game;

public class Player extends Entity {

    public double spd = 2.5;


    public boolean left, right, up, down;
    public String xDir = " ";
    public String yDir = "down";

    private boolean hasTrash = false;
    public Trash holdingItem;

    public Player(int x, int y, int width, int height, BufferedImage sprite) {
        super(x, y, width, height, sprite);

    }

    public void shoot() {
        if (hasTrash) {
            String dir = xDir + "-" + yDir;
            holdingItem.throwTrash(dir);
            hasTrash = false;
        }
    }

    public void tick() {

        for (int x = 0; x < Game.entities.size(); x++) {
            Entity e = Game.entities.get(x);
            if (e instanceof Trash) {
                
                if (checkCollision(this, e)) {

                    e.followPath(this);
                    holdingItem = (Trash)e;
                    hasTrash = true;
                    break;
                }
            }
        }

        if (left) {
            x -= spd;
            xDir = "left";
        } else if (right) {
            x += spd;
            xDir = "right";
        } else {
            if (!yDir.equals(" ")) {
                xDir = " ";
            }
        }

        if (up) {
            y -= spd;
            yDir = "up";
        } else if (down) {
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
