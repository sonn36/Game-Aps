package tiles;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import entities.Pedestrian;
import main.Game;

public class World {

    public List<Integer> historic;

    public World(){
        historic = new ArrayList<>();
    }


    public void generatePedestrian(){

        if (Game.qTrash < 10) {
            System.out.println("a");
            Random rand = new Random();
            int i = rand.nextInt(0, 10);
            if (i < 5 && !historic.contains(i)) {
                Pedestrian pedestrian = new Pedestrian(50, 100 + (i * 100), 50, 50, null, 0);
                Game.entities.add(pedestrian);
                Game.qTrash++;
            }

            if (i >= 5 && !historic.contains(i)) {
                Pedestrian pedestrian = new Pedestrian(Game.WIDTH - 100, 100 + ((i - 5) * 100), 50, 50, null, 1);
                Game.entities.add(pedestrian);
                Game.qTrash++;
            }

            historic.add(i);
        }
    }

    public void tick() {

        generatePedestrian();
        

    }

    public void render(Graphics g) {

    }

}
