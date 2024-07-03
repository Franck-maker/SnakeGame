import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Board extends JPanel implements KeyListener, ActionListener {
    private final int B_WIDTH = 700;
    private final int B_HEIGHT = 700;
    private final int TAIL_SIZE = 10;
    private final int ALL_TAILS = 900;
    private final int RAND_POS = 29;
    private final int DELAY = 140;

    private final int [] snakeXlength = new int[ALL_TAILS];
    private final int [] snakeYlength = new int [ALL_TAILS];

    private int  tails;
    private int apple_x;
    private int apple_y;

    // direction on which will move the snake
    private boolean left = false;
    private boolean right = true ;
    private boolean up = false;
    private boolean down = false;
    private boolean inGame = true;

    private Image  Headright;
    private Image ball;
    private Image apple;
    private Timer timer;


    public Board(){
        addKeyListener(this);
        initBoard();
    }


    public void initBoard(){

        addKeyListener(new TAdapter());
        setBackground(Color.black);
        setFocusable(true);


        setPreferredSize(new Dimension(B_WIDTH, B_HEIGHT));
        loadImages();
        initGame();

    }

    private void loadImages(){
        ImageIcon iit = new ImageIcon("src/tail.png");
        ball = iit.getImage();

        ImageIcon iia = new ImageIcon("src/apple.png");
        apple = iia.getImage();

        ImageIcon iih = new ImageIcon("src/Headright.png");
        Headright = iih.getImage();
    }

    private void initGame(){
        tails = 3;

        for(int i=0; i<tails; i++ ){
            snakeXlength[i] = 50 - i *10;
            snakeYlength[i] = 50;
        }

        locateApple();

        timer = new Timer(DELAY, this);
        timer.start();
    }


    @Override

    public void paintComponent(Graphics g){
        super.paintComponent(g);

        doDrawing(g);

    }

    private void doDrawing(Graphics g){
        if(inGame){

            g.drawImage(apple, apple_x, apple_y, this );

            for( int i =0; i < tails; i++){
                if(i==0){
                    g.drawImage(Headright, snakeXlength[i], snakeYlength[i], this );
                }else{
                    g.drawImage(ball, snakeXlength[i], snakeYlength[i], this);
                }

            }

            Toolkit.getDefaultToolkit().sync();

        } else {
            gameOver(g);
        }
    }

    private void gameOver (Graphics g){
        String msg = "Game Over";
        Font small = new Font("Helvetica", Font.BOLD, 14);
        FontMetrics metr = getFontMetrics(small);

        g.setColor(Color.white);
        g.setFont(small);
        g.drawString(msg, (B_WIDTH - metr.stringWidth(msg)) / 2, B_HEIGHT / 2);
    }

    private void checkApple(){
        if((snakeXlength[0] == apple_x) && (snakeYlength[0] == apple_y)){
            tails ++;
            locateApple();
        }
    }


    private void move(){
        for(int i = tails; i > 0; i--){
            snakeXlength[i] = snakeXlength[i-1];
            snakeYlength[i] = snakeYlength[i-1];

        }

        if(left){
            snakeXlength[0] -= TAIL_SIZE;
        }
        if(right){
            snakeXlength[0] += TAIL_SIZE;
        }
        if(up){
            snakeYlength[0] -= TAIL_SIZE;
        }
        if(down){
            snakeYlength[0] += TAIL_SIZE;
        }
    }

    private void checkCollision(){
        for (int i = tails; i >0; i--){

            if((i>4) && (snakeXlength[0] == snakeXlength[i]) && (snakeYlength[0] == snakeYlength[i])){
                inGame = false;
            }
        }

        if (snakeYlength[0] >= B_HEIGHT){
            inGame = false;
        }

        if(snakeYlength[0] <0){
            inGame = false;
        }
        if(snakeXlength[0] >= B_WIDTH){
            inGame = false;
        }
        if (snakeXlength[0] < 0){
            inGame = false;
        }

        if(!inGame){
            timer.stop();
        }
    }

    private void locateApple(){

        int r = (int)(Math.random()*RAND_POS);
        apple_x = ((r*TAIL_SIZE));

        r = (int)(Math.random()*RAND_POS);
        apple_y = ((r*TAIL_SIZE));
    }





    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(inGame){

            checkApple();
            checkCollision();
            move();
        }

        repaint();

    }

    private class TAdapter extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {

            int key = e.getKeyCode();

            if ((key == KeyEvent.VK_LEFT) && (!right)) {
                left = true;
                up = false;
                down = false;
            }

            if ((key == KeyEvent.VK_RIGHT) && (!left)) {
                right = true;
                up = false;
                down = false;
            }

            if ((key == KeyEvent.VK_UP) && (!down)) {
                up = true;
                right = false;
                left = false;
            }

            if ((key == KeyEvent.VK_DOWN) && (!up)) {
                down = true;
                right = false;
                left = false;
            }
        }
    }
}
