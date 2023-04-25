package main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferStrategy;
import java.util.List;

import javax.swing.JFrame;

import entities.Entity;
import entities.Player;
import entities.RecicleBin;
import graphics.Menu;
import graphics.Settings;
import graphics.UI;
import tiles.World;

public class Game extends Canvas implements Runnable, KeyListener, MouseListener {

    public static final int WIDTH = 800, HEIGHT = 600;

    public static int time = 10;
    public static int points = 10;
    public static int fps;

    public static List<Entity> entities;
    public static Player player;
    public static RecicleBin redBin, greenBin, blueBin, yellowBin, brownBin;
    public static World world;
    public static UI ui;
    public static Menu menu;
    public static Settings settings;

    private JFrame frame;
    private Thread thread;
    private boolean isRunning = false;

    public static String currentScreen = "TESTE";

    public Game() {

        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.addKeyListener(this);

        this.addMouseListener(this);
        initFrame();

        // Sound.menuMusic.play();
        world = new World();
        menu = new Menu();
        settings = new Settings();

        world.startGame();

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
            time = 30;
        }

        if (currentScreen == "MENU") {
            menu.tick();
        }
        if (currentScreen == "SETTINGS") {
            settings.tick();
        }
        if (currentScreen == "RUNNING") {
            Sound.gameSound.loop();
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

        if (currentScreen == "MENU") {
            menu.render(g);
        }
        
        if (currentScreen == "SETTINGS") {
            settings.render(g);
        }

        if (currentScreen == "RUNNING") {
            world.render(g);
            for (int i = 0; i < entities.size(); i++) {

                Entity e = entities.get(i);

                e.render(g);
            }
            ui.render(g);
        }

        if(currentScreen == "TESTE"){
            

                
            

            currentScreen = "RUNNING";


            
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
                fps = frames;
                frames = 0;
                if (time > 0 && currentScreen == "RUNNING") {
                    time--;
                }
            }
        }

    }

    public void keyTyped(KeyEvent e) {

    }

    public void keyPressed(KeyEvent e) {

        if (currentScreen == "MENU") {

            if (e.getKeyCode() == KeyEvent.VK_A) {
                menu.left = true;
            }
            if (e.getKeyCode() == KeyEvent.VK_D) {
                menu.right = true;
            }
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {

                menu.enter = true;
            }

        }

        if (currentScreen == "SETTINGS") {

            if (e.getKeyCode() == KeyEvent.VK_A) {
                settings.down = true;
            }
            if (e.getKeyCode() == KeyEvent.VK_D) {
                settings.up = true;
            }

        }

        if (currentScreen == "RUNNING") {

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

            if (e.getKeyCode() == KeyEvent.VK_SHIFT) {
                player.boost = true;
            }

        }

    }

    public void keyReleased(KeyEvent e) {

        if (currentScreen == "MENU") {

            if (e.getKeyCode() == KeyEvent.VK_A) {
                menu.left = false;
            }
            if (e.getKeyCode() == KeyEvent.VK_D) {
                menu.right = false;
            }
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                menu.enter = false;
            }

        }

        if (currentScreen == "SETTINGS") {

            if (e.getKeyCode() == KeyEvent.VK_A) {
                settings.down = false;
            }
            if (e.getKeyCode() == KeyEvent.VK_D) {
                settings.up = false;
            }

        }

        if (currentScreen == "RUNNING") {
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

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    public void mousePressed(MouseEvent e) {

        if(currentScreen == "MENU"){
            menu.mousePressed(e);
        }

        if (currentScreen == "SETTINGS"){
            settings.mousePressed(e);
            Settings.slider.mousePressed(e);
        }

        if (currentScreen == "RUNNING") {
            
            player.shoot(e);
        }


    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

}
