package conserveandcook.misc;

import conserveandcook.Database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

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
     * @throws IllegalArgumentException if score is negative or zero, or name is not 3 characters long
     */
    public void saveHighscore(int score, String name) throws IllegalArgumentException{
        // TODO: Finish the method, this is a place holder
        if (name == null || score <= 0 || name.length() != 3) {
            throw new IllegalArgumentException("score cannot be negative");
        }

        database.executeUpdate("INSERT INTO highscore (score, name) VALUES (?, ?)", score, name);
    }

    /**
     * The highscore table should be reset periodically
     */
    public void resetHighscore() {
        // TODO: Finish the method, this is a place holder
        TimerTask task = new TimerTask() {
            public void run() {
                database.executeUpdate("DELETE FROM highscore");
            }
        };
        Timer timer = new Timer("Timer");

        long period = 1000L;
        timer.schedule(task, 0, period);
    }

    /**
     *
     * @return Hashmap with key being the name and value being the score
     */
    public Map<String, Integer> getHighscore() {
        // TODO: Finish the method, this is a place holder
        Map<String, Integer> highscore = new HashMap<>();
        ResultSet result = database.executeQuery("SELECT score, name FROM highscore");
        try {
            while (result.next()){
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
