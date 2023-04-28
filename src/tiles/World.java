package tiles;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import entities.Entity;
import entities.Pedestrian;
import entities.Player;
import entities.RecicleBin;
import graphics.UI;
import main.Game;

public class World {

    public List<Integer> historic;
    public int qTrash = 0;

    public World() {
        historic = new ArrayList<>();
    }

    public void startGame() {

        Game.entities = new ArrayList<Entity>();
        Game.world = new World();
        Game.ui = new UI();

        Game.player = new Player(Game.WIDTH / 2 - 25, Game.HEIGHT / 2 - 25, 50, 50, null);

        Game.redBin = new RecicleBin(100, 50, 50, 50, null, "red");
        Game.greenBin = new RecicleBin(200, 50, 50, 50, null, "green");
        Game.blueBin = new RecicleBin(300, 50, 50, 50, null, "blue");
        Game.yellowBin = new RecicleBin(400, 50, 50, 50, null, "yellow");
        Game.brownBin = new RecicleBin(500, 50, 50, 50, null, "brown");

        Game.entities.add(Game.redBin);
        Game.entities.add(Game.greenBin);
        Game.entities.add(Game.blueBin);
        Game.entities.add(Game.yellowBin);
        Game.entities.add(Game.brownBin);

        Game.entities.add(Game.player);

        Game.time = 30;
        Game.points = 0;

    }

    public void generatePedestrian() {

        if (qTrash < 10) {
            Random rand = new Random();
            int i = rand.nextInt(0, 10);
            if (i < 5 && !historic.contains(i)) {
                Pedestrian pedestrian = new Pedestrian(0 - 50, 100 + (i * 100 + (rand.nextInt(0, 100))), 50, 50, null, 0);
                Game.entities.add(pedestrian);
                qTrash++;
                historic.add(i);
            }

            if (i >= 5 && !historic.contains(i)) {
                Pedestrian pedestrian = new Pedestrian(Game.WIDTH, 100 + ((i - 5) * 100 + (rand.nextInt(0, 100))), 50, 50, null, 1);
                Game.entities.add(pedestrian);
                qTrash++;
                historic.add(i);
            }

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
