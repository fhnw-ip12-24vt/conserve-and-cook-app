package conserveandcook.model;

import conserveandcook.AbstractTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ApplicationTest extends AbstractTest {
    @Test
    public void testStartScreen() {
        Application model = new Application();
        String screen = model.getCurrentScreen();
        assertEquals("start", screen);
    }

    @Test
    public void testIncScreen() {
        Application model = new Application();
        model.incrementScreen();
        String screen = model.getCurrentScreen();
        assertEquals("language", screen);
    }

    @Test
    public void testDecScreen() {
        Application model = new Application();
        model.incrementScreen();
        model.incrementScreen();
        model.decrementScreen();
        String screen = model.getCurrentScreen();
        assertEquals("language", screen);
        model.decrementScreen();
        screen = model.getCurrentScreen();
        assertEquals("start", screen);
        model.decrementScreen();
        screen = model.getCurrentScreen();
        assertEquals("start", screen);
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
}
