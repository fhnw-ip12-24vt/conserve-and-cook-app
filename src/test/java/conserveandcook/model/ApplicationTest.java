package conserveandcook.model;

import conserveandcook.AbstractTest;
import conserveandcook.view.gui.AvailableScreens;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ApplicationTest extends AbstractTest {
    @Test
    public void testStartScreen() {
        Application model = new Application();
        AvailableScreens screen = model.getCurrentScreen();
        assertEquals(AvailableScreens.START, screen);
    }

    @Test
    public void testIncScreen() {
        Application model = new Application();
        model.incrementScreen();
        AvailableScreens screen = model.getCurrentScreen();
        assertEquals(AvailableScreens.LANGUAGE, screen);
    }

    @Test
    public void testDecScreen() {
        Application model = new Application();
        model.incrementScreen();
        model.incrementScreen();
        model.decrementScreen();
        AvailableScreens screen = model.getCurrentScreen();
        assertEquals(AvailableScreens.LANGUAGE, screen);
        model.decrementScreen();
        screen = model.getCurrentScreen();
        assertEquals(AvailableScreens.START, screen);
        model.decrementScreen();
        screen = model.getCurrentScreen();
        assertEquals(AvailableScreens.START, screen);
    }

    @Test
    public void testLanguageSelect() {
        Application model = new Application();
        String lang = model.getSelectedLanguage();
        assertEquals("de", lang);
    }

    @Test
    public void testIncLanguageSelect() {
        Application model = new Application();
        String[] langs = Application.getLanguages();
        String lang;
        lang = model.getSelectedLanguage();
        assertEquals("de", lang);
        for (int i = 0; i < langs.length - 1; i++) {
            model.incrementLanguage();
            lang = model.getSelectedLanguage();
            assertEquals(langs[3 - i], lang);
        }
        // Looping
        model.incrementLanguage();
        lang = model.getSelectedLanguage();
        assertEquals("de", lang);
    }

    @Test
    public void getLanguages() {
        String[] langs = {"de", "fr", "it", "en"};
        String[] appLangs = Application.getLanguages();
        for (int i = 0; i < langs.length; i++) {
            assertEquals(langs[i], appLangs[i]);
        }
    }

    @Test
    public void testInc(){
        Application model = new Application();
        int max = 3;
        int current = 2;

        current = model.incrementWrapped(current, max);
        assertEquals(3, current);

        current = model.incrementWrapped(current, max);
        assertEquals(0, current);

        current = model.incrementWrapped(current, max);
        assertEquals(1, current);

        current = model.incrementWrapped(current, max);
        assertEquals(2, current);

        current = model.incrementWrapped(current, max);
        assertEquals(3, current);
    }

    @Test
    public void testDec(){
        Application model = new Application();
        int max = 4;
        int current = 2;

        current = model.decrementWrapped(current, max);
        assertEquals(1, current);

        current = model.decrementWrapped(current, max);
        assertEquals(0, current);

        current = model.decrementWrapped(current, max);
        assertEquals(4, current);

        current = model.decrementWrapped(current, max);
        assertEquals(3, current);
    }
}
