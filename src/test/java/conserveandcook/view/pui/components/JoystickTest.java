package conserveandcook.view.pui.components;

import conserveandcook.AbstractTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class JoystickTest extends AbstractTest {
    private Joystick js;
    private boolean test = false;

    @BeforeEach
    public void reset() {
        test = false;
    }

    @Test
    public void testCreateJoystick() {
        // Random file, obviously pom.xml is not a valid joystick input
        js = new Joystick("pom.xml");
    }

    @Test
    public void testOnNorth() {
        js = new Joystick("pom.xml");
        js.onNorth(this::task);
        js.mockInput();
        assertTrue(test);
    }

    @Test
    public void testOnEast() {
        js = new Joystick("pom.xml");
        js.onEast(this::task);
        js.mockInput();
        assertTrue(test);
    }

    @Test
    public void testOnSouth() {
        js = new Joystick("pom.xml");
        js.onSouth(this::task);
        js.mockInput();
        assertTrue(test);
    }

    @Test
    public void testOnWest() {
        js = new Joystick("pom.xml");
        js.onWest(this::task);
        js.mockInput();
        assertTrue(test);
    }

    private void task() {
        test = true;
    }
}
