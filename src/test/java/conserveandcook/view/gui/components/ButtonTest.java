package conserveandcook.view.gui.components;

import conserveandcook.AbstractTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ButtonTest extends AbstractTest {
    String test;

    @Test
    public void testRunnable() {
        // Arrange
        Button b = new Button(() -> test = "Hello");

        // Act
        b.setState(Button.States.ACTIVE);
        b.mockPress();

        // Assert
        assertEquals("Hello", test);
    }

    @Test
    public void testIdleState() {
        // Arrange
        Button b = new Button(() -> test = "Hello");

        // Act
        b.setState(Button.States.IDLE);
        b.mockPress();

        // Assert
        assertEquals("", test);
    }

    @Test
    public void testDisabledState() {
        // Arrange
        Button b = new Button(() -> test = "Hello");

        // Act
        b.setState(Button.States.DISABLED);
        b.mockPress();

        // Assert
        assertEquals("", test);
    }

    @BeforeEach
    public void setUp() {
        test = "";
    }
}
