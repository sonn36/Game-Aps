package graphics;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import main.Game;

public class Menu {

    public boolean left, right, enter;
    public int option = 0, maxOptions = 1;


    public void tick(){

        if(enter){

            if(option == 0){
                Game.world.startGame();
                Game.currentScreen = "RUNNING";
            }

            enter = false;
        }

        if(left){

            option--;

            if(option < 0){
                option = maxOptions;
            }

            left = false;

        }
        if(right){

            option++;

            if(option > maxOptions){
                option = 0;
            }

            right = false;
        }


    }

    public void render(Graphics g){

        g.setColor(Color.gray);
        g.fillRect(100, 100, Game.WIDTH - 200, Game.HEIGHT - 200);

        g.setColor(Color.GREEN);
        g.fillRect(148 + (option * 270), 398 , 234, 74);

        
        g.setColor(Color.BLUE);
        g.fillRect(150, 400, 230, 70);
        g.setColor(Color.WHITE);
        g.drawString("Jogar novamente", 220, 400 + 40);

        g.setColor(Color.BLUE);
        g.fillRect(420, 400, 230, 70);
        g.setColor(Color.WHITE);
        g.drawString("Sair", 520, 400 + 40);



        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 120));
        g.drawString(String.valueOf(Game.points), Game.WIDTH / 2 - String.valueOf(Game.points).length(), 260);

        
    }
    
}
