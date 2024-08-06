// La classe Snake gère uniquement les mouvements et l'état du serpent. (SRP)
public class Snake {
    private final int[] x;
    private final int[] y;
    private int tails;

    public Snake(int maxTails, int initialSize) {
        x = new int[maxTails];
        y = new int[maxTails];
        tails = initialSize;
        for (int i = 0; i < tails; i++) {
            x[i] = 50 - i * 10;
            y[i] = 50;
        }
    }

    public void move(boolean left, boolean right, boolean up, boolean down, int tailSize) {
        for (int i = tails; i > 0; i--) {
            x[i] = x[i - 1];
            y[i] = y[i - 1];
        }
        if (left) {
            x[0] -= tailSize;
        } else if (right) {
            x[0] += tailSize;
        } else if (up) {
            y[0] -= tailSize;
        } else if (down) {
            y[0] += tailSize;
        }
    }

    public int[] getX() { return x; }
    public int[] getY() { return y; }
    public int getTails() { return tails; }
    public void grow() { tails++; }
}

