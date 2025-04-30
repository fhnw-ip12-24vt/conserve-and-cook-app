package conserveandcook.model;

import java.util.ArrayList;

public class Boot {
    private ArrayList<String> logs = new ArrayList<>();
    private int capacity = 10;
    private boolean success = false;

    public void log(String log) {
        if (log == null) throw new IllegalArgumentException("Log must not be null");

        if (logs.size() >= capacity) {
            logs.removeFirst();
        }

        logs.add(log);
    }

    public ArrayList<String> getLogs() {
        return logs;
    }

    public boolean checksPass() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
