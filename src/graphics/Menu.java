package graphics;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import main.Game;
import main.Sound;

public class Menu extends Screen{


    public boolean left, right;
    public int maxOptions = 2;

    public Menu(){
        option = 0;

        for(int i = 0; i <= maxOptions; i++){
            addButton(420, 150 + (i * 100), 230, 70, i);
        }
    }


    public void tick(){


        if(enter){

            if(option == 0){
                Sound.gameSound.play();
                Game.world.startGame();
                Game.currentScreen = "RUNNING";
            }

            if(option == 1){
                Game.settings.lastScreen = Game.currentScreen;
                Game.currentScreen = "SETTINGS";
            }

            if(option == 2){
                System.exit(1);
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
        g.fillRect(418 , 148 + (option * 100) , 234, 74);

        
        g.setColor(Color.BLUE);
        g.fillRect(420, 150, 230, 70);
        g.setColor(Color.WHITE);
        g.drawString("Jogar novamente", 420, 150 + 40);

        g.setColor(Color.BLUE);
        g.fillRect(420, 250, 230, 70);
        g.setColor(Color.WHITE);
        g.drawString("Configs", 420, 250 + 40);

        g.setColor(Color.BLUE);
        g.fillRect(420, 350, 230, 70);
        g.setColor(Color.WHITE);
        g.drawString("Sair", 420, 350 + 40);



        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 120));
        g.drawString(String.valueOf(Game.points), (((Game.WIDTH - 200)/ 2) / 2) , 260);

        
    }

    
}
