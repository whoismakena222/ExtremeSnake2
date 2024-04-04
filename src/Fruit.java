import java.awt.*;

public class Fruit {
        public String name;
        public int xpos;
        public int ypos;

        public int dx;

        public int dy;

        public int points;

        public int width = 75;
        public int height = 75;

        public Rectangle rec;

        boolean isAlive = false;

    public Fruit (String paramName, int paramPoints, int paramXpos, int paramYpos) {
        name = paramName;
        points = paramPoints;
        xpos = paramXpos;
        ypos = paramYpos;
        rec = new Rectangle(xpos,ypos,width, height);
    }
    public void spawn(){
        xpos = xpos + dx;
        ypos = ypos + dy;

        dy = dy - 1;



    }


}
