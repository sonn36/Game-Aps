package entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.List;

import main.Game;

public class Trash extends Entity {

    private String color;

    public boolean throwingTrash = false;
    private int q = 0, force = 50;

    public int firstY;

    private int x0, y0, x1, y1;
    List<List<Integer>> mousePosition;

    public Trash(int x, int y, int width, int height, BufferedImage sprite, String color) {
        super(x, y, width, height, sprite);
        this.color = color;
        this.firstY = y;
    }

    public void throwTrash(int x, int y, int force) {


        this.force = force;
        
        x0 = this.getX();
        y0 = this.getY();
        x1 = x;
        y1 = y;

        mousePosition = Game.ui.getPath(x0, y0, x1, y1);
        q = 0;
        
        throwingTrash = true;
    }

    public void changeDir() {

    }

    public void tick() {

        if (throwingTrash) {


            if (q < force) {


                int i = q * 3;

                try {

                    if (x0 > x1) {
                        this.setX(mousePosition.get(0).get((mousePosition.get(0).size() - 1) - i));

                    } else {
                        this.setX(mousePosition.get(0).get(i));
                    }

                    if (y0 > y1) {
                        this.setY(mousePosition.get(1).get((mousePosition.get(1).size() - 1) - i));

                    } else {
                        this.setY(mousePosition.get(1).get(i));
                    }
                } catch (IndexOutOfBoundsException e) {

                }

                q++;

            } else {
                throwingTrash = false;
            }

        } else {
            if (checkCollision(this, Game.player)
                    && (Game.player.holdingItem == this || Game.player.holdingItem == null)) {

                followPath(Game.player);
                Game.player.holdingItem = (Trash) this;
            }
        }

        if (checkCollision(this, Game.yellowBin) && color.equals("yellow") ||
                checkCollision(this, Game.greenBin) && color.equals("green") ||
                checkCollision(this, Game.blueBin) && color.equals("blue") ||
                checkCollision(this, Game.redBin) && color.equals("red") ||
                checkCollision(this, Game.brownBin) && color.equals("brown")) {

            Game.points += 10;

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
