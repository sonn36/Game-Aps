package graphics;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import main.Game;

public class UI extends Screen {

    private int q1 = 0, q2 = 0;

    public boolean pressKey = false, wrong, right;

    public void render(Graphics g) {
        if (Game.time > 0) {
            g.setColor(Color.WHITE);
            g.fillRect(50, Game.HEIGHT - 50, (int) Game.player.stamina * 2, 20);

            g.drawString(String.valueOf(Game.time), Game.WIDTH / 2, Game.HEIGHT - 50);
            g.drawString(String.valueOf(Game.points), Game.WIDTH / 2, 10);

            g.setColor(Color.red);

            g.drawString("FPS: " + Game.fps, 10, 10);

        } else {

            g.setColor(new Color(0, 200, 255, 100));
            g.fillRect(0, 0, Game.WIDTH, Game.HEIGHT);

            g.setFont(new Font("Arial", Font.BOLD, q1));
            g.setColor(Color.WHITE);
            g.drawString("Time´s Up", (Game.WIDTH / 2 - 50) - (q1 * 2) - 5, (Game.HEIGHT / 2) + 5);

            g.setColor(Color.RED);
            g.drawString("Time´s Up", (Game.WIDTH / 2 - 50) - (q1 * 2), (Game.HEIGHT / 2));
            if (q1 < 100) {
                q1 += 5;
            } else {

                if (q2 == 100) {
                    q2 = 0;
                    pressKey = true;
                }
                if (q2 < 50) {

                    g.setFont(new Font("Arial", Font.BOLD, 15));
                    g.drawString("press any key to continue!", Game.WIDTH / 2 - 100, Game.HEIGHT / 2 + 100);

                }

                q2++;
            }
        }

    }

}
