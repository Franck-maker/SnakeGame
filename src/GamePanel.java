// La classe GamePanel gère uniquement l'affichage et le dessin des éléments du jeu. (SRP)
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GamePanel extends JPanel {
    private Image headRight;
    private Image ball;
    private Image apple;
    private Image watermelon;

    public GamePanel() {
        setBackground(Color.black);
        setFocusable(true);
        setPreferredSize(new Dimension(500, 500));
        loadImages();
    }

    private void loadImages() {
        ImageIcon iit = new ImageIcon("src/tail.png");
        ball = iit.getImage();
        ImageIcon iia = new ImageIcon("src/apple.png");
        apple = iia.getImage();
        ImageIcon iiw = new ImageIcon("src/watermelon.png");
        watermelon = iiw.getImage();
        ImageIcon iih = new ImageIcon("src/Headright.png");
        headRight = iih.getImage();
    }

    public void draw(Graphics g, Snake snake, Apple apple, Watermelon watermelon, GameStats stats) {
        g.drawImage(this.apple, apple.getX(), apple.getY(), this);
        if (watermelon.isVisible()) {
            g.drawImage(this.watermelon, watermelon.getX(), watermelon.getY(), this);
        }

        int[] snakeX = snake.getX();
        int[] snakeY = snake.getY();
        for (int i = 0; i < snake.getTails(); i++) {
            if (i == 0) {
                g.drawImage(headRight, snakeX[i], snakeY[i], this);
            } else {
                g.drawImage(ball, snakeX[i], snakeY[i], this);
            }
        }

        drawScore(g, stats.getScore());
    }

    private void drawScore(Graphics g, int score) {
        String scoreMsg = "Score: " + score;
        Font small = new Font("Helvetica", Font.BOLD, 14);
        g.setColor(Color.green);
        g.setFont(small);
        g.drawString(scoreMsg, 10, 10);
    }

    public void gameOver(Graphics g, GameStats stats) {
        String msg = "Game Over";
        Font small = new Font("Helvetica", Font.BOLD, 14);
        FontMetrics metr = getFontMetrics(small);

        g.setColor(Color.red);
        g.setFont(small);
        g.drawString(msg, (500 - metr.stringWidth(msg)) / 2, 500 / 2);

        String scoreMsg = "Score: " + stats.getScore();
        g.setFont(new Font("serif", Font.BOLD, 20));
        g.drawString(scoreMsg, (500 - metr.stringWidth(scoreMsg)) / 2, 500 / 2 + 30);

        String restartMsg = "Press Enter to restart";
        g.setFont(new Font("serif", Font.BOLD, 20));
        g.drawString(restartMsg, (500 - metr.stringWidth(restartMsg)) / 2, 500 / 2 + 60);


        showStats(g, stats);
    }

    private void showStats(Graphics g, GameStats stats) {
        Font small = new Font("Helvetica", Font.BOLD, 14);
        g.setColor(Color.white);
        g.setFont(small);

        ArrayList<String> playerNames = stats.getPlayerNames();
        ArrayList<Integer> playerScores = stats.getPlayerScores();
        for (int i = 0; i < playerNames.size(); i++) {
            String stat = playerNames.get(i) + ": " + playerScores.get(i);
            g.drawString(stat, 10, 30 + i * 20);
        }
    }


}
