import java.awt.*;
public class Snake {
    public Image pic;
    public int xpos;
    public int ypos;
    public int dx = 1;
    public int dy = 1;

    public int points;
    public int width = 75;
    public int height = 75;

    public boolean upIsPressed;
    public boolean downIsPressed;
    public boolean leftIsPressed;
    public boolean rightIsPressed;
    public boolean isAlive = false;

    public Rectangle rec;

    public Snake(int paramXpos, int paramYpos) {
        xpos = paramXpos;
        ypos = paramYpos;
        rec = new Rectangle(xpos, ypos, width, height);
    }
    public void move(){
        if (upIsPressed == true){
            for (int x = 0; x < 5; x++) {
                ypos = ypos - dy;
            }
        }
        if (downIsPressed == true){
            for (int x = 0; x < 5; x++) {
            ypos = ypos + dy;
            }
        }
        if (leftIsPressed == true ){
            for (int x = 0; x < 5; x++) {
            xpos = xpos - dx;}
        }
        if (rightIsPressed == true) {
            for (int x = 0; x < 5; x++) {
                xpos = xpos + dx;
            }
            rec = new Rectangle(xpos, ypos, width, height);

//            if (ypos < 0 || ypos > 700 - height || xpos > 1000 - width || xpos < 0) {
//                xpos = 100;
//                ypos = 250;
//            }
        }

    }
}
