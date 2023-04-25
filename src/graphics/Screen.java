package graphics;

import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class Screen {

    public List<Btn> buttons;
    public int option;
    public boolean enter;

    public Screen() {
        buttons = new ArrayList<>();
    }

    class Btn {
        public int x, y, width, height, index;

        public Btn(int x, int y, int widht, int height, int index) {
            this.x = x;
            this.y = y;
            this.width = widht;
            this.height = height;
            this.index = index;

        }
    }

    public List<List<Integer>> getPath(int x0, int y0, int x1, int y1) {


        if(x0 > x1){
            int r = x0;
            x0 = x1;
            x1 = r;
        }
        if(y0 > y1){
            int r = y0;
            y0 = y1;
            y1 = r;
        }


        List<Integer> xValues = new ArrayList<>();
        List<Integer> yValues = new ArrayList<>();
        List<List<Integer>> values = new ArrayList<>();

        int dx = x1 - x0;
        int dy = y1 - y0;

        int d = 2 * dy - dx;

        int x = x0, y = y0;

        int incE = 2 * dy;
        int incNE = 2 * (dy - dx);

        xValues.add(x);
        yValues.add(y);



        
        while (x < x1) {
            if (d < 0) {
                d += incE;

            } else {
                d += incNE;
                y++;
            }
            x++;

            xValues.add(x);
            yValues.add(y);
        }
    

        values.add(xValues);
        values.add(yValues);

        return values;
    }

    public void addButton(int x, int y, int width, int height, int index) {
        Btn btn = new Btn(x, y, width, height, index);
        buttons.add(btn);
    }

    public void mousePressed(MouseEvent e) {

        int xx = e.getX();
        int yy = e.getY();

        for (int i = 0; i < buttons.size(); i++) {

            Btn en = buttons.get(i);
            if (yy >= en.y && yy <= en.y + en.height) {
                if (xx >= en.x && xx <= en.x + en.width) {
                    this.option = en.index;
                    this.enter = true;
                }
            }
        }

    }
}
