import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.tools.Tool;

public class Main implements Runnable, KeyListener {
    final int WIDTH = 1000;
    final int HEIGHT = 800;
    public JFrame frame;
    public Canvas canvas;
    public JPanel panel;
    public BufferStrategy bufferStrategy;

    public boolean gamePlaying = false;
    public boolean gameOver = false;

    public boolean isPaused = false;

    public Image backgroundPic;
    public Fruit strawberry;
    public Fruit watermelon;
    public Snake[] dots;

    public Image strawberryPic;
    public Image watermelonPic;

    public Image bombPic;

    public Bomb bomb;
    public int aliveDot;

    public SoundFile eatStarSound;



    public static void main(String[] args) {
        Main ex = new Main();
        new Thread(ex).start();
    }

    public Main() {
        setUpGraphics();
        canvas.addKeyListener(this);

        strawberry = new Fruit("strawberry", 1,750, 400);
        strawberryPic = Toolkit.getDefaultToolkit().getImage("strawberry.png");

        watermelon = new Fruit("watermelon", 5, 600, 200);
        watermelonPic = Toolkit.getDefaultToolkit().getImage("watermelon.png");

        bomb = new Bomb("bomb",5, 200, 250);
        bombPic = Toolkit.getDefaultToolkit().getImage("bomb.png");

        backgroundPic = Toolkit.getDefaultToolkit().getImage("gamebackground2.jpeg");

        dots = new Snake[100];
//        dots[0] = new Snake(100, 250);
        for (int x = 0; x < dots.length; x++) {
            dots[x] = new Snake(x * 50 + 100, 400);
            dots[x].pic = Toolkit.getDefaultToolkit().getImage("character red.png");
        }
        dots[0].isAlive = true;
        dots[1].isAlive = true;
        dots[2].isAlive = true;
        aliveDot = 3;

        eatStarSound = new SoundFile("Comical Pop and Swirl.wav");
    }

    public void run() {
        while (true) {
            if (gamePlaying == true && isPaused == false){
                moveThings();
            }
            collisions();
            render();
            pause(10);
        }
    }

    public void moveThings() {
        for (int x = 0; x < dots.length; x++) {
            dots[x].move();
        }
        bomb.spawn();
        Fruit.spawnn();
    }


    public void collisions() {
        for (int x = 0; x < dots.length; x++) {
            if (strawberry.rec.intersects(dots[x].rec) && dots[x].isAlive == true && strawberry.isAlive == true) {
                strawberry.isAlive = false;
                dots[aliveDot].isAlive = true;
                aliveDot++;
            }
        }
        for (int x = 0; x < dots.length; x++) {
            if (watermelon.rec.intersects(dots[x].rec)) {
                watermelon.isAlive = false;
            }
        }
        for (int x = 0; x < dots.length; x++) {
            if (bomb.rec.intersects(dots[x].rec)) {
                bomb.isAlive = false;
            }
        }
    }

    public void render() {
        Graphics2D g = (Graphics2D) bufferStrategy.getDrawGraphics();
        g.clearRect(0, 0, WIDTH, HEIGHT);

        if(gamePlaying == false){ // start screen
            g.setColor(Color.black); //
            g.fillRect(0,0, WIDTH,HEIGHT);
            g.setColor(Color.white);
            g.setFont(new Font("Times Roman", Font.PLAIN, 60));
            g.drawString("choose your character", 200, 200);
            // character selection
        }
        else if(gamePlaying == true && gameOver == false) {

            g.drawImage(backgroundPic, 0, 0, WIDTH, HEIGHT, null);

            if (strawberry.isAlive == true) {
                g.drawImage(strawberryPic, strawberry.xpos, strawberry.ypos, strawberry.width, strawberry.height, null);
            }

            if (watermelon.isAlive == true) {
                g.drawImage(watermelonPic, watermelon.xpos, watermelon.ypos, watermelon.width, watermelon.height, null);
            }

            if (bomb.isAlive == true) {
                g.drawImage(bombPic, bomb.xpos, bomb.ypos, bomb.width, bomb.height, null);
            }

            for (int x = 0; x < 5; x++) {
                if (dots[x].isAlive == true) {
                    g.drawImage(dots[x].pic, dots[x].xpos, dots[x].ypos, dots[x].width, dots[x].height, null);
                }
            }
        }

        else {
            g.setColor(Color.black);// points total
            g.fillRect(0,0,WIDTH, HEIGHT);
            g.setColor(Color.yellow);
            g.setFont(new Font("Times Roman", Font.PLAIN, 60));
            g.drawString ("score: " + dots[x].points, 200, 200);
            }

        g.dispose();
        bufferStrategy.show();
    }

    public void pause(int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
        }
    }

    private void setUpGraphics() {
        frame = new JFrame("Application Template");

        panel = (JPanel) frame.getContentPane();
        panel.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        panel.setLayout(null);

        canvas = new Canvas();
        canvas.setBounds(0, 0, WIDTH, HEIGHT);
        canvas.setIgnoreRepaint(true);

        panel.add(canvas);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setResizable(false);
        frame.setVisible(true);

        // sets up things so the screen displays images nicely.
        canvas.createBufferStrategy(2);
        bufferStrategy = canvas.getBufferStrategy();
        canvas.requestFocus();
    }

    public void keyTyped(KeyEvent e) {
    }

    public void keyPressed(KeyEvent e) {
        char key = e.getKeyChar();
        int keyCode = e.getKeyCode();
        System.out.println("Key pressed: " + key + ", Key code: " + keyCode);
        if (keyCode == 38) {
            for (int x = 0; x < 5; x++) {
                dots[x].upIsPressed = true;
            }
        }// up
        if (keyCode == 40) {
            for (int x = 0; x < 5; x++) {
                dots[x].downIsPressed = true;
            }
        } // down
        if (keyCode == 37) {
            for (int x = 0; x < 5; x++) {
                dots[x].leftIsPressed = true;
            }
        } // left
        if (keyCode == 39) {
            for (int x = 0; x < 5; x++) {
                dots[x].rightIsPressed = true;
            }
        } // right
    }

    public void keyReleased (KeyEvent e) {
    }
}


