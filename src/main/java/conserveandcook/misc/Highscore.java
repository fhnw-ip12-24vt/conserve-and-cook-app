package conserveandcook.misc;

import conserveandcook.Database;

import java.util.HashMap;
import java.util.Map;

public class Highscore {
    private final Database database = Database.getInstance();
    private static Highscore INSTANCE;

    private Highscore() {}

    public static Highscore getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Highscore();
        }
        return INSTANCE;
    }

    /**
     * Save the player's score into the highscore table
     *
     * @param score
     * @param name
     * @throws IllegalArgumentException if score is negative, or name is not 3 characters long
     */
    public void saveHighscore(int score, String name) throws IllegalArgumentException{
        // TODO: Finish the method, this is a place holder
        if (score <= 0) {
            throw new IllegalArgumentException("score cannot be negative");
        }
        database.executeUpdate("INSERT INTO highscore (score, name) VALUES (?, ?)", score, name);
    }

    /**
     * The highscore table should be reset periodically
     */
    public void resetHighscore() {
        // TODO: Finish the method, this is a place holder
        database.executeUpdate("DELETE FROM highscore");
    }

    /**
     *
     * @return Hashmap with key being the name and value being the score
     */
    public Map<String, Integer> getHighscore() {
        // TODO: Finish the method, this is a place holder
        Map<String, Integer> highscore = new HashMap<>();

        return highscore;
    }
}
