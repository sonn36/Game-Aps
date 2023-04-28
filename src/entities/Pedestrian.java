package entities;

import java.awt.image.BufferedImage;
import java.util.Random;

import main.Game;

public class Pedestrian extends Entity {

    private int pos, force, frame, i;
    private String[] colors = { "red", "green", "blue", "yellow", "brown" };
    private boolean back = false;
    private Trash trash;

    public Pedestrian(int x, int y, int width, int height, BufferedImage sprite, int pos) {
        super(x, y, width, height, sprite);
        this.pos = pos;

        Random rand = new Random();

        i = rand.nextInt(0, 4);
        force = rand.nextInt(10, 100);

    }

    public void addTrash() {

        trash = new Trash(getX(), getY(), 25, 25, null, colors[i]);
        Game.entities.add(trash);
        trash.throwTrash(Game.WIDTH / 2, getY(), force);

    }

    public void tick() {

        if (!back) {

            if (frame < 30) {

                if (pos == 0) {
                    setX(getX() + 1);
                } else {
                    setX(getX() - 1);
                }
                frame++;
            } else {
                addTrash();
                frame = 0;
                back = true;
            }
        } else {
            if (frame < 30) {

                if (pos == 0) {
                    setX(getX() - 1);
                } else {
                    setX(getX() + 1);
                }
                frame++;
            } else {
                frame = 0;
                Game.entities.remove(this);
            }
        }

    }
}
