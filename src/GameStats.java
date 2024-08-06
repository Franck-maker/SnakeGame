// La classe GameStats gère uniquement les scores et les statistiques des joueurs. (SRP)
import java.util.ArrayList;

public class GameStats {
    private int score;
    private ArrayList<String> playerNames;
    private ArrayList<Integer> playerScores;

    public GameStats() {
        playerNames = new ArrayList<>();
        playerScores = new ArrayList<>();
        score = 0;
    }

    public void addPlayer(String playerName) {
        playerNames.add(playerName);
        playerScores.add(0);
    }

    public void updateScore(int points) {
        score += points;
    }

    public void savePlayerScore(String playerName) {
        int index = playerNames.indexOf(playerName);
        if (index != -1) {
            playerScores.set(index, score);
        }
    }
    public void displayFinalStats(String playerName) {
        int index = playerNames.indexOf(playerName);
        if (index != -1) {
            int finalScore = playerScores.get(index);
            System.out.println(playerName + ": " + finalScore);
        }
    }

    public int getScore() { return score; }
    public ArrayList<String> getPlayerNames() { return playerNames; }
    public ArrayList<Integer> getPlayerScores() { return playerScores; }
}

