// La classe Apple gère uniquement la position et la génération de la pomme. (SRP)
public class Apple implements Food{
    private int x;
    private int y;

    public void locate(int randPos, int tailSize) {
        x = ((int) (Math.random() * randPos)) * tailSize;
        y = ((int) (Math.random() * randPos)) * tailSize;
    }

    public int getX() { return x; }
    public int getY() { return y; }

    @Override
    public boolean isVisible() {
        return false;
    }

    @Override
    public void setVisible(boolean visible) {

    }
}

