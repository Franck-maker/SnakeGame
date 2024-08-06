public interface Food {
    void locate(int randPos, int tailSize);
    int getX();
    int getY();
    boolean isVisible();
    void setVisible(boolean visible);
}


