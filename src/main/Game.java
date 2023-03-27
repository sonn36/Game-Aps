package main;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

public class Game extends Canvas implements Runnable, KeyListener {


    public static final int WIDTH = 800, HEIGHT = 600;

    private JFrame frame;
    private Thread thread;
    private boolean isRunning = false;


    public Game(){

        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        initFrame();

    }

    public void initFrame(){

        frame = new JFrame();
        frame.setTitle("teste");
        frame.add(this);
        frame.pack();
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);


    }

    public void tick(){

    }

    public void render(){

    }


    public synchronized void start(){
        thread = new Thread(this);
        thread.start();
    }

    public synchronized void stop(){

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
            while (delta >= 1) {
                tick();
                render();
                delta--;
            }
            frames++;
            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                System.out.println("FPS: " + frames);
                frames = 0;
            }
        }
    
    }

    public void keyTyped(KeyEvent e) {

    }

    public void keyPressed(KeyEvent e) {

    }

    public void keyReleased(KeyEvent e) {

    }
    
}
