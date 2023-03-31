package entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;


public class Entity {

    protected double x, y;
    protected int width, height;

    public Entity(double x, double y, int width, int height, BufferedImage sprite) {

        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

    }

    public int getX() {
        return (int)this.x;
    }

    public void setX(int x){
        this.x = x;
    }

    public int getY() {
        return (int)this.y;
    }

    public void setY(int y){
        this.y = y;
    }


    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    public void tick() {

    }

    public void render(Graphics g) {

        g.setColor(Color.WHITE);
        g.fillRect(this.getX(), this.getY(), this.width, this.height);
    }


    public void followPath(Entity e1){

        setX(e1.getX() + (e1.getWidth() / 2) /2);
        setY(e1.getY() + (e1.getHeight() / 2) /2);
    }

    public boolean checkCollision(Entity e1, Entity e2){
        Rectangle r1 = new Rectangle(e1.getX(), e1.getY(), e1.getWidth(), e1.getHeight());
        Rectangle r2 = new Rectangle(e2.getX(), e2.getY(), e2.getWidth(), e2.getHeight());

        if(r1.intersects(r2)){
            return true;
        }

        return false;
    }

}
