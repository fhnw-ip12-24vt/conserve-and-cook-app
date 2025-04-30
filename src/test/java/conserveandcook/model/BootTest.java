package conserveandcook.model;

import conserveandcook.AbstractTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class BootTest extends AbstractTest {
    private Boot b;
    private final String[] logMessages = {
            "HI", "aowfiej", "456789o", "abcabc", "234567890098765432",
            "HI", "aowfiej", "456789o", "abcabc", "234567890098765432",
    };

    @BeforeEach
    public void initializeBoot() {
        b = new Boot();
    }

    @Test
    public void testAddOne() {
        // Act
        b.log("HI");

        // Assert
        assertEquals("HI", b.getLogs().getFirst());
    }

    @Test
    public void testAddMany() {
        // Act
        b.log("HI");
        b.log("THIS IS A LOG");

        // Assert
        assertEquals("HI", b.getLogs().getFirst());
        assertEquals("THIS IS A LOG", b.getLogs().get(1));
    }

    @Test
    public void testNull() {
        assertThrows(IllegalArgumentException.class, () -> b.log(null));
    }

    @Test
    public void testOverflow() {
        // Arrange
        for (String s : logMessages) {
            b.log(s);
        }

        // Act
        b.log("11th Log");

        // Assert
        assertEquals("11th Log", b.getLogs().getLast());
        assertEquals(logMessages[1], b.getLogs().getFirst());
    }
}
