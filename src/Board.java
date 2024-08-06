// La classe Board gère la logique du jeu et coordonne les autres classes. (SRP)
// La classe Board ne dépend pas des détails de l'implémentation des autres classes, mais de leurs abstractions. (DIP)
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
//import java.util.ArrayList;

public class Board extends JPanel implements KeyListener, ActionListener {
    private final int B_WIDTH = 500;
    private final int B_HEIGHT = 500;
    private final int TAIL_SIZE = 10;
    private final int ALL_TAILS = 900;
    private final int RAND_POS = 29;
    private int DELAY = 200;

    private Snake snake;
    private Apple apple;
    private Watermelon watermelon;
    private GameStats stats;
    private GamePanel gamePanel;


    private boolean left = false;
    private boolean right = true;
    private boolean up = false;
    private boolean down = false;
    private boolean inGame = false;
    private boolean paused = false;

    private Timer timer;
    private long startTime;


    public Board() {
        initBoard();
    }

    private void initBoard() {
        addKeyListener(this);
        setBackground(Color.black);
        setFocusable(true);
        setPreferredSize(new Dimension(B_WIDTH, B_HEIGHT));

        gamePanel = new GamePanel();

        // Asking the player name
        String playerName = JOptionPane.showInputDialog(this, "Enter your name:");
        if (playerName == null || playerName.trim().isEmpty()) {

            playerName = "Player";
        }




        initGame();
    }

    private void initGame() {




        showMessage("Press Enter to start the Game.");
        snake = new Snake(ALL_TAILS, 3);
        apple = new Apple();
        apple.locate(RAND_POS, TAIL_SIZE);
        watermelon = new Watermelon();
        stats = new GameStats();


        timer = new Timer(DELAY, this);
        timer.start();
        startTime = System.currentTimeMillis();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);


        if (inGame) {
            gamePanel.draw(g, snake, apple, watermelon, stats);
        } else {
            gamePanel.gameOver(g, stats);
        }
        Toolkit.getDefaultToolkit().sync();
    }

    private void checkApple() {
        int[] snakeX = snake.getX();
        int[] snakeY = snake.getY();
        if ((snakeX[0] == apple.getX()) && (snakeY[0] == apple.getY())) {
            snake.grow();
            stats.updateScore(10);
            apple.locate(RAND_POS, TAIL_SIZE);
            if (Math.random() < 0.3) {
                watermelon.locateWatermelon(RAND_POS, TAIL_SIZE);
            }
        } else if (watermelon.isVisible() && (snakeX[0] == watermelon.getX()) && (snakeY[0] == watermelon.getY())) {
            snake.grow();
            snake.grow();
            stats.updateScore(20);
            watermelon.hide();
        }
    }




    private void checkCollision() {
        int[] snakeX = snake.getX();
        int[] snakeY = snake.getY();
        for (int i = snake.getTails(); i > 0; i--) {
            if ((i > 4) && (snakeX[0] == snakeX[i]) && (snakeY[0] == snakeY[i])) {
                inGame = false;
            }
        }

        if (snakeY[0] >= B_HEIGHT || snakeY[0] < 0 || snakeX[0] >= B_WIDTH || snakeX[0] < 0) {
            inGame = false;
        }

        if (!inGame) {
            timer.stop();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (inGame && !paused) {
            checkApple();
            checkCollision();
            snake.move(left, right, up, down, TAIL_SIZE);
            repaint();

            if (System.currentTimeMillis() - startTime > 5000) {
                DELAY -= 10;
                timer.setDelay(DELAY);
                startTime = System.currentTimeMillis();
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) { }

    @Override
    public void keyPressed(KeyEvent e) { }

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

        if (inGame && !paused) {
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
        stats.updateScore(-stats.getScore());
        startTime = System.currentTimeMillis();

        snake = new Snake(ALL_TAILS, 3);
        apple.locate(RAND_POS, TAIL_SIZE);
        watermelon.hide();

        timer.setDelay(DELAY);
        timer.start();
    }

    private void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }
}
