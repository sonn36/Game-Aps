package entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import main.Game;

public class Trash extends Entity {

    private String color;

    public int firstY;

    public Trash(int x, int y, int width, int height, BufferedImage sprite, String color) {
        super(x, y, width, height, sprite);
        this.color = color;
        this.firstY = y;
    }

    public void throwTrash(String dir) {

        String[] pos = dir.split("-");

        if (pos[1].equals("up")) {

            this.setY(this.getY() - 150);
        }

        else if (pos[1].equals("down")) {

            this.setY(this.getY() + 150);
        }
        if (pos[0].equals("left")) {

            this.setX(this.getX() - 150);
        } else if (pos[0].equals("right")) {

            this.setX(this.getX() + 150);
        }

    }

    public void tick() {

        if (checkCollision(this, Game.yellowBin) && color.equals("yellow") ||
                checkCollision(this, Game.greenBin) && color.equals("green") ||
                checkCollision(this, Game.blueBin) && color.equals("blue") ||
                checkCollision(this, Game.redBin) && color.equals("red") ||
                checkCollision(this, Game.brownBin) && color.equals("brown")) {

            Game.entities.remove(this);

        }

    }

    public void render(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(getX(), getY(), getWidth(), getHeight());
        if (color.equals("red")) {
            g.setColor(Color.RED);
        }
        if (color.equals("green")) {
            g.setColor(Color.GREEN);
        }
        if (color.equals("blue")) {
            g.setColor(Color.BLUE);
        }
        if (color.equals("yellow")) {
            g.setColor(Color.YELLOW);
        }
        if (color.equals("brown")) {
            g.setColor(Color.CYAN);
        }
        g.fillRect(getX() + 1, getY() + 1, getWidth() - 2, getHeight() - 2);
    }

}
