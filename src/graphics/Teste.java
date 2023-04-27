package graphics;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.util.List;

import main.Game;

public class Teste extends Screen {

    private int mouseX = 0, mouseY = 0;
    private List<List<Integer>> mousePosition;
    private int x0 = 0, x1 = 0, y0 = 0, y1 = 0;

    public void mousePressed(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY();

        mousePosition = Game.ui.getPath(Game.player.getX(), Game.player.getY(), mouseX, mouseY);

         x0 = mousePosition.get(0).get(0);
         y0 = mousePosition.get(1).get(0);

         x1 = mousePosition.get(0).get(mousePosition.get(0).size() - 1);
         y1 = mousePosition.get(1).get(mousePosition.get(0).size() - 1);

        if (mouseX < Game.player.getX()) {
            x0 = mousePosition.get(0).get(mousePosition.get(0).size() - 1);
            x1 = mousePosition.get(0).get(0);
            // System.out.println("x0: "+ mousePosition.get(0).get(0) + " x1:" +
            // mousePosition.get(0).get(mousePosition.size()));

        }
        if (mouseY < Game.player.getY()) {
            y0 = mousePosition.get(1).get(mousePosition.get(1).size() - 1);
            y1 = mousePosition.get(1).get(0);
            // System.out.println("y0: "+ mousePosition.get(1).get(0) + " y1:" +
            // mousePosition.get(1).get(mousePosition.size()));

        }
    }

    public void render(Graphics g) {

        g.setColor(Color.yellow);
        g.fillRect(Game.WIDTH /2, 0, 400, 300);
        g.setColor(Color.blue);
        g.fillRect(Game.WIDTH /2, Game.HEIGHT / 2, 400, 300);

        g.setColor(Color.red);

        g.setFont(new Font("Arial", Font.BOLD, 25));

        g.drawString("playerX:" + Game.player.getX(), Game.WIDTH / 2, 50);
        g.drawString("playerY:" + Game.player.getY(), Game.WIDTH / 2, 100);
        g.drawString("mouseX:" + mouseX, Game.WIDTH / 2, 150);
        g.drawString("mouseY:" + mouseY, Game.WIDTH / 2, 200);
        try {


        
            g.drawString("x0:" + x0 + " x1:" + x1, 10, 300);
            g.drawString("y0:" + y0 + " y1:" + y1, 10, 400);


            g.setFont(new Font("Arial", Font.BOLD, 11));
            g.drawString(""+mousePosition.get(0).subList(0, 30), 10, 450);
            g.drawString(""+mousePosition.get(0).subList(31, 60), 10, 500);
            g.drawString(""+mousePosition.get(1), 10, 550);

            g.setColor(Color.MAGENTA);
            g.fillRect(mouseX, 0, 1, mouseY);
            g.fillRect(0, mouseY, mouseX, 1);
        } catch (Exception e) {

        }

    }

}
