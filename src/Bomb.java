import java.awt.*;
public class Bomb {
    public String name;
    public int xpos;
    public int ypos;

    public int points;

    public int dx;
    public int dy;
    public int width = 75;
    public int height = 75;

    public Rectangle rec;

    boolean isAlive = true;

    public Bomb (String paramName,int paramPoints, int paramXpos, int paramYpos) {
        name = paramName;
        xpos = paramXpos;
        ypos = paramYpos;
        points = paramPoints;
        rec = new Rectangle(xpos,ypos,width, height);
    }
    public void spawn(){
        xpos = xpos + dx;
        ypos = ypos + dy;
        if (ypos < -100) {
            ypos = 700;
        }
        if (ypos > 700){
            ypos = -100;
        }
        if (xpos > 1000){
            xpos = -100;
        }
        if (xpos < -100){
            xpos = 1000;
        }
        rec = new Rectangle(xpos,ypos,width, height);
    }
}
