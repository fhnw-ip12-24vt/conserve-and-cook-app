package conserveandcook.misc;

import conserveandcook.Database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import static ch.mvcbase.MvcLogger.LOGGER;


public class Highscore {
    public static final int NAME_LENGTH = 3;
    private final Database database = Database.getInstance();
    private static Highscore INSTANCE;

    private Map<String, Integer> scores = new HashMap<>();

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
     * @param score of a player
     * @param name of a player in the game
     * @throws IllegalArgumentException if score is negative, or name is not 3 characters long
     */
    public void saveHighscore(int score, String name) throws IllegalArgumentException{
       // validate arguments
        if (name == null || score < 0 || name.length() != NAME_LENGTH) {
            throw new IllegalArgumentException("score cannot be negative");
        }
        // only once
        var result = scores.get(name);
        if (result != null && result == score) {
            return;
        }
        scores.put(name,score);
        // Update
        LOGGER.logInfo(String.format("About to insert a highscore, name: %s, score: %s ", name, score));
        database.executeUpdate("INSERT INTO highscore (score, name) VALUES (?, ?)", score, name);
        LOGGER.logInfo("Done.");

    }

    /**
     * The highscore table should be reset periodically
     */
    public void resetHighscore() {
        database.executeUpdate("DELETE FROM highscore");
    }

    /**
     * @return Hashmap with key being the name and value being the score
     */
    public Map<String, Integer> getHighscore() {
        Map<String, Integer> highscore = new HashMap<>();
        //returning the newest score
        ResultSet result = database.executeQuery("SELECT score, name FROM highscore order by id asc");
        try {
            while (result.next()) {
              int score =  result.getInt("score");
              String name = result.getString("name");
              highscore.put(name,score);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return highscore;
    }
}
