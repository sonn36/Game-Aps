package graphics;

import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;


public class Screen {
    
    public List<Btn> buttons;
    public int option;
    public boolean enter;

    public Screen(){
        buttons = new ArrayList<>();
    }
    class Btn{
     public int x, y, width, height, index;


     public Btn(int x, int y, int widht, int height, int index){
        this.x = x;
        this.y = y;
        this.width = widht;
        this.height = height;
        this.index = index;


     }
    }


    public void addButton(int x, int y, int width, int height, int index){
        Btn btn = new Btn(x, y, width, height, index);
        buttons.add(btn);
    }

    

    public void mousePressed(MouseEvent e){

        int xx = e.getX();
        int yy = e.getY();

        for(int i = 0; i < buttons.size(); i++){

            Btn en = buttons.get(i);
            if(yy >= en.y && yy <= en.y + en.height){
                if(xx >= en.x && xx <= en.x + en.width){
                    this.option = en.index;
                    this.enter = true;
                }
            }
        }


    }
}
