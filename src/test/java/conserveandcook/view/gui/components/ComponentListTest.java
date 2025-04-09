package conserveandcook.view.gui.components;

import conserveandcook.AbstractTest;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ComponentListTest extends AbstractTest {
    String s;

    @Test
    public void drawTest() {
        // Arrange
        ComponentList list = new ComponentList();
        Button b = new Button(() -> s = "hello", 0, 0);

        // Act
        list.add(b);


        // Assert
        // If NPE is thrown, we know the component's draw was called
        assertThrows(NullPointerException.class, () -> list.drawEach(null));
    }

    @Test
    public void drawManyTest() {
        // Arrange
        ComponentList list = new ComponentList();
        Button b = new Button(() -> s = "hello", 0, 0);
        Button d = new Button(() -> s = "hello", 0, 0);
        List<Button> arrayList = List.of(b,d);

        // Act
        list.addMany(arrayList);


        // Assert
        // If NPE is thrown, we know the component's draw was called
        assertEquals(2, list.size());
        assertThrows(NullPointerException.class, () -> list.drawEach(null));
    }

}
