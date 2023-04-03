package main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import entities.Entity;
import entities.Player;
import entities.RecicleBin;
import graphics.Menu;
import graphics.UI;
import tiles.World;

public class Game extends Canvas implements Runnable, KeyListener {

    public static final int WIDTH = 800, HEIGHT = 600;

    public static int time = 30;
    public static int points = 0;

    public static List<Entity> entities;
    public static Player player;
    public static RecicleBin redBin, greenBin, blueBin, yellowBin, brownBin;
    public static World world;
    public static UI ui;
    public static Menu menu;

    private JFrame frame;
    private Thread thread;
    private boolean isRunning = false;

    private String currentScreen = "RUNNING";

    public Game() {

        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.addKeyListener(this);
        initFrame();

        entities = new ArrayList<Entity>();

        player = new Player(WIDTH / 2 - 25, HEIGHT / 2 - 25, 50, 50, null);

        redBin = new RecicleBin(100, 50, 50, 50, null, "red");
        greenBin = new RecicleBin(200, 50, 50, 50, null, "green");
        blueBin = new RecicleBin(300, 50, 50, 50, null, "blue");
        yellowBin = new RecicleBin(400, 50, 50, 50, null, "yellow");
        brownBin = new RecicleBin(500, 50, 50, 50, null, "brown");

        world = new World();
        menu = new Menu();
        ui = new UI();

        entities.add(redBin);
        entities.add(greenBin);
        entities.add(blueBin);
        entities.add(yellowBin);
        entities.add(brownBin);

        entities.add(player);

    }

    public void initFrame() {

        frame = new JFrame();
        frame.setTitle("teste");
        frame.add(this);
        frame.pack();
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }

    public void tick() {

        if (time <= 0) {
            currentScreen = "MENU";
        }

        if (currentScreen == "MENU") {
            menu.tick();
        }

        if (currentScreen == "RUNNING") {
            world.tick();
            for (int i = 0; i < entities.size(); i++) {

                Entity e = entities.get(i);

                e.tick();
            }
        }

    }

    public void render() {

        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null) {
            this.createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();

        g.setColor(Color.BLACK);
        g.fillRect(0, 0, WIDTH, HEIGHT);

        if(currentScreen == "MENU"){
            menu.render(g);
        }

        if (currentScreen == "RUNNING") {
            world.render(g);
            for (int i = 0; i < entities.size(); i++) {

                Entity e = entities.get(i);

                e.render(g);
            }
            ui.render(g);
        }

        g.dispose();
        bs.show();
    }

    public synchronized void start() {
        thread = new Thread(this);
        thread.start();
        isRunning = true;
    }

    public synchronized void stop() {

    }

    public static void main(String[] args) {

        Game game = new Game();
        game.start();
    }

    public void run() {

        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;
        while (isRunning) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            if (delta >= 1) {
                tick();
                render();
                delta = 0;
                frames++;
            }
            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                System.out.println("FPS: " + frames);
                frames = 0;
                if (time > 0) {
                    time--;
                }
            }
        }

    }

    public void keyTyped(KeyEvent e) {

    }

    public void keyPressed(KeyEvent e) {

        if (e.getKeyCode() == KeyEvent.VK_W) {
            player.up = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_S) {
            player.down = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_A) {
            player.left = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_D) {
            player.right = true;
        }

        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            player.shoot();
        }
        if (e.getKeyCode() == KeyEvent.VK_SHIFT) {
            player.boost = true;
        }

    }

    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_W) {
            player.up = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_S) {
            player.down = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_A) {
            player.left = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_D) {
            player.right = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_SHIFT) {
            player.boost = false;
        }

    }

}
