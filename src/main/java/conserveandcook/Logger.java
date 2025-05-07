package conserveandcook;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.Queue;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.SimpleFormatter;

public final class Logger {
    private static final String LOGGER_NAME = "Conserve & Cook | Logging";
    private static final int MAX_LOG_FILE_SIZE = 1024 * 1024 * 5; // 5 MB
    private static final int LOG_FILE_COUNT = 3; // Keep 3 rotating files
    private static final int DEFAULT_BUFFER_SIZE = 100;

    public static final Logger LOGGER = new Logger();

    private final java.util.logging.Logger logger;
    private final Queue<String> errorBuffer;
    private final int bufferSize;

    private Logger() {
        this(DEFAULT_BUFFER_SIZE);
    }

    public Logger(int bufferSize) {
        this.logger = java.util.logging.Logger.getLogger(LOGGER_NAME);
        this.bufferSize = bufferSize;
        this.errorBuffer = new LinkedList<>();

        configureLogger();
    }

    private void configureLogger() {
        logger.setLevel(Level.INFO);
        logger.setUseParentHandlers(false);

        // Console output for development
        ConsoleHandler consoleHandler = new ConsoleHandler();
        consoleHandler.setLevel(Level.INFO);
        logger.addHandler(consoleHandler);

        // File output for production
        try {
            Files.createDirectories(Paths.get("logs"));
            FileHandler fileHandler = new FileHandler("logs/application.log", MAX_LOG_FILE_SIZE, LOG_FILE_COUNT, true);
            fileHandler.setFormatter(new SimpleFormatter());
            fileHandler.setLevel(Level.INFO);
            logger.addHandler(fileHandler);
        } catch (IOException e) {
            System.err.println("Failed to setup file logging: " + e.getMessage());
        }
    }

    private synchronized void bufferError(String formattedMessage) {
        if (errorBuffer.size() >= bufferSize) {
            errorBuffer.poll(); // remove oldest
        }
        errorBuffer.offer(formattedMessage);
    }

    public synchronized String[] getErrorLogBuffer() {
        return errorBuffer.toArray(new String[0]);
    }

    public void info(String msg, Object... args) {
        logger.info(() -> String.format(msg, args));
    }

    public void error(String msg, Object... args) {
        String formatted = String.format(msg, args);
        logger.severe(formatted);
        bufferError(formatted);
    }

    public void debug(String msg, Object... args) {
        logger.fine(() -> String.format(msg, args));
    }

    public void exception(String msg, Throwable exception) {
        logger.log(Level.SEVERE, msg, exception);
        bufferError(msg + " Exception: " + exception.toString());
    }
}