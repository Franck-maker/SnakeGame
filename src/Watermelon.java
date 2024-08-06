// La classe Watermelon hérite de Apple et ajoute la gestion de visibilité. (LSP)
public class Watermelon extends Apple {
    private boolean isVisible;

    public void locateWatermelon(int randPos, int tailSize) {
        super.locate(randPos, tailSize);
        isVisible = true;
    }

    public boolean isVisible() { return isVisible; }
    public void hide() { isVisible = false; }
}


