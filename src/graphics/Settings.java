package graphics;

import java.awt.Color;
import java.awt.Graphics;

import extras.Slider;
import main.Game;
import main.Sound;

public class Settings extends Screen {

    public boolean up, down;
    private float volume = 0;
    private boolean teste = false;
    public static Slider slider = new Slider(150, 150, 500, 25);

    public String lastScreen;

    public Settings() {

        addButton(420, 350, 230, 70, 0);
    }

    public void tick() {

        if (enter) {

            if (option == 0) {
                Game.currentScreen = lastScreen;
            }
            enter = false;
        }

        if (!teste) {
            Sound.gameSound.play();
            teste = true;
        }

        volume = (float) slider.getValue() / 500;
        Sound.gameSound.setVolume(volume);
    }

    public void render(Graphics g) {

        g.setColor(Color.gray);
        g.fillRect(100, 100, Game.WIDTH - 200, Game.HEIGHT - 200);

        slider.render(g);

        g.drawString("Volume : " + String.valueOf(slider.getValue() / 5), 150, 150);

        g.setColor(Color.BLUE);
        g.fillRect(420, 350, 230, 70);
        g.setColor(Color.WHITE);
        g.drawString("Voltar", 420, 350 + 40);
    }

}
