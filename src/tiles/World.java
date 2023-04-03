package tiles;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import entities.Pedestrian;
import main.Game;

public class World {

    public List<Integer> historic;
    public int qTrash = 0;

    public World() {
        historic = new ArrayList<>();
    }

    public void generatePedestrian() {

        if (qTrash < 10) {
            Random rand = new Random();
            int i = rand.nextInt(0, 10);
            if (i < 5 && !historic.contains(i)) {
                Pedestrian pedestrian = new Pedestrian(50, 100 + (i * 100), 50, 50, null, 0);
                Game.entities.add(pedestrian);
                qTrash++;
            }

            if (i >= 5 && !historic.contains(i)) {
                Pedestrian pedestrian = new Pedestrian(Game.WIDTH - 100, 100 + ((i - 5) * 100), 50, 50, null, 1);
                Game.entities.add(pedestrian);
                qTrash++;
            }

            historic.add(i);
        }
    }

    public void tick() {

        for (int yy = 0; yy < Game.HEIGHT / 50; yy++) {
            for (int xx = 0; xx < Game.WIDTH / 50; xx++) {

            }
        }

        generatePedestrian();

    }

    public void render(Graphics g) {

    }

}
