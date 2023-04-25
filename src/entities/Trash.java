package entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.List;

import main.Game;

public class Trash extends Entity {

    private String color;

    private int xDir = 0, yDir = 0;
    public boolean throwingTrash = false;
    private int force = 50;

    public int firstY;

    public Trash(int x, int y, int width, int height, BufferedImage sprite, String color) {
        super(x, y, width, height, sprite);
        this.color = color;
        this.firstY = y;
    }

    public void throwTrash(int x, int y) {

        xDir = x;
        yDir = y;
        throwingTrash = true;

    }

    public void changeDir() {
        
    }

    public void tick() {

        if (throwingTrash) {

            System.out.println(xDir);
            System.out.println(yDir);
            List<List<Integer>> mousePosition = Game.ui.getPath(this.getX(), this.getY(), xDir, yDir);
            if(xDir > this.getX()){
                System.out.println("x0: "+ mousePosition.get(0).get(0) + " x1:" + mousePosition.get(0).get(mousePosition.size()));
                
            }
            if(yDir > this.getY()){
                System.out.println("y0: "+ mousePosition.get(1).get(0) + " y1:" + mousePosition.get(1).get(mousePosition.size()));

            }
            for(int q = 0; q < force; q++){

                //System.out.println("x: "+ mousePosition.get(0).get(q) + " y:" + mousePosition.get(1).get(q));
                //this.setX(mousePosition.get(0).get(q));
                //this.setY(mousePosition.get(1).get(q));

            }

            throwingTrash = false;

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
