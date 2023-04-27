package entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import main.Game;

public class Player extends Entity {

    public double spd = 2.5;
    public boolean boost = false;
    public double stamina = 50;
    public double maxStamina = 50;

    public int force = 0;
    public boolean charge = false;

    public boolean left, right, up, down;

    public boolean hasTrash = false;
    public Trash holdingItem;

    public Player(int x, int y, int width, int height, BufferedImage sprite) {
        super(x, y, width, height, sprite);

    }

    public void shoot(int x, int y) {
        if (holdingItem != null) {

            holdingItem.throwTrash(x, y, force);
            force = 0;
            holdingItem = null;
        }
    }

    public void tick() {

        spd = 2.5;
        if (charge && holdingItem != null) {

            if (stamina > 0) {

                if (force < 50) {
                    force += 5;
                }
                stamina--;
            } else {
                shoot(this.getX() + this.getWidth() / 4, this.getY() + this.getHeight() / 4);
            }

        }

        if (boost) {
            if (stamina > 0) {
                spd = 5.0;
                stamina--;
            }
        } else {
            if (stamina < maxStamina) {
                stamina += 0.5;
            }
        }

        for (int x = 0; x < Game.entities.size(); x++) {
            Entity e = Game.entities.get(x);
            if (e instanceof Trash) {

                if (checkCollision(this, e)) {

                    break;
                }

                if (x == Game.entities.size() - 1) {
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
