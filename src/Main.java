import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        // Créez une fenêtre de jeu
        JFrame frame = new JFrame("Snake Game");

        // Ajoutez une instance de Board à la fenêtre
        Board board = new Board();
        frame.add(board);

        // Configurez les propriétés de la fenêtre
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.pack();
        frame.setLocationRelativeTo(null);  // Centre la fenêtre sur l'écran
        frame.setVisible(true);
    }
}
