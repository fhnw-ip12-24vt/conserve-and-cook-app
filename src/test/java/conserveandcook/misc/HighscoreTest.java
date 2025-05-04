package conserveandcook.misc;

import conserveandcook.AbstractTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.params.provider.Arguments.arguments;

public class HighscoreTest extends AbstractTest {

    @BeforeEach
    public void tearDown() {
        Highscore highscore = Highscore.getInstance();
        highscore.resetHighscore();
    }

    @ParameterizedTest
    @MethodSource("highscoreArguments")
    @Order(1)
    public void testAddHighscore(int score, String name) {
        // Arrange
       Highscore highscore = Highscore.getInstance();
       Map<String, Integer> highscoreMap;
       int size;

       // Act
       highscore.saveHighscore(score, name);
       highscoreMap = highscore.getHighscore();

       // Assert
        size = highscoreMap.size();
        assertEquals(1, size);

        assertEquals(score, highscoreMap.get(name));
    }

    @ParameterizedTest
    @MethodSource("illegalHighscoreArguments")
    @Order(3)
    public void testIllegalHighscore(int score, String name) {
        // Arrange
        Highscore highscore = Highscore.getInstance();
        Map<String, Integer> highscoreMap;
        // Act
        assertThrows(IllegalArgumentException.class, () -> highscore.saveHighscore(score, name));
        highscoreMap = highscore.getHighscore();

        // Assert
        int size = highscoreMap.size();
        assertEquals(0, size);
    }

    @Test
    @Order(2)
    public void testResetHighscore() throws InterruptedException {
        // Arrange
        Highscore highscore = Highscore.getInstance();
        Map<String, Integer> highscoreMap;
        int size;

        // Act
        highscore.resetHighscore();
        highscoreMap = highscore.getHighscore();
        size = highscoreMap.size();

        // Assert
        assertEquals(0, size);
    }

    private static Stream<Arguments> highscoreArguments() {
        return Stream.of(
                arguments(1, "ABC"),
                arguments(400, "ABC"),
                arguments(567892345, "ABC")
        );
    }

    private static Stream<Arguments> illegalHighscoreArguments() {
        return Stream.of(
                // TODO (Elena, 4.3) : Unclear whether 0 is illegal or not? Assumed to be legal, there for below commented
                // arguments(0, "ABC"),
                arguments(-400, "ABC"),
                arguments(12, ""),
                arguments(12, "SDFGHJKLKJH"),
                arguments(0, null)
        );
    }
}
