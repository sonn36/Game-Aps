package extras;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Slider implements MouseListener {

    private int x, y, width, height;
    private int value = 0;
    public boolean enabled = false;

    public Slider(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public int getValue(){
        return value;
    }

    public void render(Graphics g) {

        g.setColor(Color.WHITE);
        g.fillRect(x, y, width, height);

        g.setColor(Color.BLUE);
        g.fillRect(x, y, value , height);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) 
    {


        int xx = e.getX();
        int yy = e.getY();

        

        if(yy >= y && yy <= y + height){
            if(xx >= x && xx <= x + width){
                value = xx - x;
            }
        }
        
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

}
