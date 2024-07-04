import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;



public class Board extends JPanel implements KeyListener, ActionListener {
    private final int B_WIDTH = 500;
    private final int B_HEIGHT = 500;
    private final int TAIL_SIZE = 10;
    private final int ALL_TAILS = 900;
    private final int RAND_POS = 29;
    private int DELAY = 200;

    private final int SPEED_INCREMENT_INTERVAL = 5000; // incresease speed every 5 seconds

    private final int[] snakeXlength = new int[ALL_TAILS];
    private final int[] snakeYlength = new int[ALL_TAILS];

    private int tails;
    private int apple_x;
    private int apple_y;
    private int score;
    private int wm_x;
    private int wm_y;
    private boolean iswm;

    // direction on which will move the snake
    private boolean left = false;
    private boolean right = true;
    private boolean up = false;
    private boolean down = false;
    private boolean inGame = false;
    private boolean paused = false;

    private Image Headright;
    private Image ball;
    private Image apple;
    private Image watermelon;
    private Timer timer;
    private long startTime;
    private String playerName;
    private ArrayList<String> playerNames = new ArrayList<>();
    private ArrayList<Integer> playerScores = new ArrayList<>();


    public Board() {

        initBoard();
    }


    public void initBoard() {

        //addKeyListener(new TAdapter());
        setBackground(Color.black);
        setFocusable(true);


        setPreferredSize(new Dimension(B_WIDTH, B_HEIGHT));
        loadImages();
        addKeyListener(this); // to listen to the keyboard


        // Asking the player name
        int i = 0;
        playerName = JOptionPane.showInputDialog(this, "Enter your name:");
        if (playerName == null || playerName.trim().isEmpty()) {
            i++;
            playerName = "Player" + i;
        }

        playerNames.add(playerName);
        playerScores.add(0);

        showMessage("Press Enter to start the Game.");

        initGame();

    }

    private void loadImages() {
        ImageIcon iit = new ImageIcon("src/tail.png");
        ball = iit.getImage();

        ImageIcon iia = new ImageIcon("src/apple.png");
        apple = iia.getImage();

        ImageIcon iiw = new ImageIcon("src/watermelon.png");
        watermelon = iiw.getImage();

        ImageIcon iih = new ImageIcon("src/Headright.png");
        Headright = iih.getImage();
    }

    private void initGame() {
        tails = 3;
        score = 0;

        for (int i = 0; i < tails; i++) {
            snakeXlength[i] = 50 - i * TAIL_SIZE;
            snakeYlength[i] = 50;
        }

        locateApple();


        timer = new Timer(DELAY, this);
        timer.start();
    }


    @Override

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        doDrawing(g);

    }

    private void doDrawing(Graphics g) {
        if (inGame) {

            g.drawImage(apple, apple_x, apple_y, this);
            if (iswm) {
                g.drawImage(watermelon, wm_x, wm_y, this);
            }

            for (int i = 0; i < tails; i++) {
                if (i == 0) {
                    g.drawImage(Headright, snakeXlength[i], snakeYlength[i], this);
                } else {
                    g.drawImage(ball, snakeXlength[i], snakeYlength[i], this);
                }


            }
            drawScore(g);


            Toolkit.getDefaultToolkit().sync();

        } else if (!paused) {
            gameOver(g);
        }
    }

    private void drawScore(Graphics g) {
        String scoreMsg = "Score: " + score;
        Font small = new Font("Helvetica", Font.BOLD, 14);
        g.setColor(Color.green);
        g.setFont(small);
        g.drawString(scoreMsg, 10, 10);
    }

    private void gameOver(Graphics g) {
        String msg = "Game Over";
        Font small = new Font("Helvetica", Font.BOLD, 14);
        FontMetrics metr = getFontMetrics(small);

        g.setColor(Color.red);
        g.setFont(small);
        g.drawString(msg, (B_WIDTH - metr.stringWidth(msg)) / 2, B_HEIGHT / 2);

        String scoreMsg = "Score: " + score;
        g.setFont(new Font("serif", Font.BOLD, 20));
        g.drawString(scoreMsg, (B_WIDTH - metr.stringWidth(scoreMsg)) / 2, B_HEIGHT / 2 + 30);

        String restartMsg = "Press Enter to restart";
        g.setFont(new Font("serif", Font.BOLD, 20));
        g.drawString(restartMsg, (B_WIDTH - metr.stringWidth(restartMsg)) / 2, B_HEIGHT / 2 + 60);

        playerScores.set(playerNames.indexOf(playerName), score);
        showStats(g);
    }

    private void showStats(Graphics g) {
        Font small = new Font("Helvetica", Font.BOLD, 14);
        g.setColor(Color.white);
        g.setFont(small);

        for (int i = 0; i < playerNames.size(); i++) {
            String stat = playerNames.get(i) + ": " + playerScores.get(i);
            g.drawString(stat, 10, 30 + i * 20);
        }
    }

    private void checkApple() {
        if ((snakeXlength[0] == apple_x) && (snakeYlength[0] == apple_y)) {
            tails++;
            score += 10;
            locateApple();
            if (Math.random() < 0.3) { // 30% of watermelon
                locatewatermelon();
            }
        } else if (iswm && (snakeXlength[0] == wm_x) && (snakeYlength[0] == wm_y)) {
            tails += 2;
            score += 20;
            iswm = false;
        }
    }


    private void move() {
        for (int i = tails; i > 0; i--) {
            snakeXlength[i] = snakeXlength[i - 1];
            snakeYlength[i] = snakeYlength[i - 1];

        }

        if (left) {
            snakeXlength[0] -= TAIL_SIZE;
        }
        if (right) {
            snakeXlength[0] += TAIL_SIZE;
        }
        if (up) {
            snakeYlength[0] -= TAIL_SIZE;
        }
        if (down) {
            snakeYlength[0] += TAIL_SIZE;
        }
    }

    private void checkCollision() {
        for (int i = tails; i > 0; i--) {

            if ((i > 4) && (snakeXlength[0] == snakeXlength[i]) && (snakeYlength[0] == snakeYlength[i])) {
                inGame = false;
            }
        }

        if (snakeYlength[0] >= B_HEIGHT) {
            inGame = false;
        }

        if (snakeYlength[0] < 0) {
            inGame = false;
        }
        if (snakeXlength[0] >= B_WIDTH) {
            inGame = false;
        }
        if (snakeXlength[0] < 0) {
            inGame = false;
        }

        if (!inGame) {
            timer.stop();
        }
    }

    private void locateApple() {

        int r = (int) (Math.random() * RAND_POS);
        apple_x = ((r * TAIL_SIZE));

        r = (int) (Math.random() * RAND_POS);
        apple_y = ((r * TAIL_SIZE));
    }

    private void locatewatermelon() {
        int r = (int) (Math.random() * RAND_POS);
        wm_x = ((r * TAIL_SIZE));

        r = (int) (Math.random() * RAND_POS);
        wm_x = ((r * TAIL_SIZE));
        iswm = true;
    }

    private void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }


    @Override
    public void actionPerformed(ActionEvent e) {

        if (inGame && !paused) {

            checkApple();
            checkCollision();
            move();
            repaint();

            if (System.currentTimeMillis() - startTime > SPEED_INCREMENT_INTERVAL) {
                DELAY -= 10;
                timer.setDelay(DELAY);
                startTime = System.currentTimeMillis();
            }
        }


    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_SPACE) {
            if (paused) {
                timer.start();
                paused = false;
            } else {
                timer.stop();
                paused = true;
            }
        }

        if (key == KeyEvent.VK_ENTER && !inGame) {
            restartGame();
        }

        if (key == KeyEvent.VK_ENTER && !inGame) {
            inGame = true;
            startTime = System.currentTimeMillis();
            timer.start();
        }

        if (inGame && !paused){

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

    private void restartGame() {
        inGame = true;
        paused = false;
        DELAY = 200;
        left = false;
        right = true;
        up = false;
        down = false;
        score = 0;
        startTime = System.currentTimeMillis();

        // Réinitialiser la taille et la position du serpent
        tails = 3;
        for (int i = 0; i < tails; i++) {
            snakeXlength[i] = 50 - i * TAIL_SIZE;
            snakeYlength[i] = 50;
        }
        locateApple();
        iswm = false;

        timer.setDelay(DELAY);
        timer.start();
    }

}
