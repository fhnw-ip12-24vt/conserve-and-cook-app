package conserveandcook.model;

import conserveandcook.AbstractTest;
import conserveandcook.view.gui.AvailableScreens;
import org.junit.jupiter.api.Test;
import conserveandcook.misc.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
        Languages lang = model.getSelectedLanguage();
        assertEquals(Languages.DE, lang);
    }

    @Test
    public void testIncLanguageSelect() {
        Application model = new Application();
        Languages[] langs = Application.getLanguages();
        Languages lang;
        lang = model.getSelectedLanguage();
        assertEquals(Languages.DE, lang);
        for (int i = 0; i < langs.length - 1; i++) {
            model.incrementLanguage();
            lang = model.getSelectedLanguage();
            assertEquals(langs[3 - i], lang);
        }
        // Looping
        model.incrementLanguage();
        lang = model.getSelectedLanguage();
        assertEquals(Languages.DE, lang);
    }

    @Test
    public void getLanguages() {
        String[] langs = {"DE", "FR", "IT", "EN"};
        Languages[] appLangs = Application.getLanguages();
        for (int i = 0; i < langs.length; i++) {
            assertEquals(langs[i], appLangs[i].name());
        }
    }

    @Test
    public void testNextRegion() {
        Application model = new Application();

        Regions region = model.getSelectedRegion();
        assertEquals(Regions.AMERICA, region);

        model.nextRegion();
        region = model.getSelectedRegion();
        assertEquals(Regions.ASIA, region);

        model.nextRegion();
        region = model.getSelectedRegion();
        assertEquals(Regions.EUROPE, region);

        model.nextRegion();
        region = model.getSelectedRegion();
        assertEquals(Regions.AMERICA, region);
    }

    @Test
    public void testPreviousRegion() {
        Application model = new Application();

        model.previousRegion();
        Regions region = model.getSelectedRegion();
        assertEquals(Regions.EUROPE, region);

        model.previousRegion();
        region = model.getSelectedRegion();
        assertEquals(Regions.ASIA, region);

        model.previousRegion();
        region = model.getSelectedRegion();
        assertEquals(Regions.AMERICA, region);

        model.previousRegion();
        region = model.getSelectedRegion();
        assertEquals(Regions.EUROPE, region);
    }

    @Test
    public void testNameCharIncrement() {
        Application model = new Application();

        for (int i = 0; i < Application.NAME_LENGTH; i++) {
            int selectedChar = model.getSelectedChar();
            assertEquals(i, selectedChar);
            model.incrementSelectedChar();
        }

        int selectedChar = model.getSelectedChar();
        assertEquals(0, selectedChar);
    }

    @Test
    public void testNameCharDecrement() {
        Application model = new Application();

        for (int i = Application.NAME_LENGTH - 1; i >= 0; i--) {
            model.decrementSelectedChar();
            int selectedChar = model.getSelectedChar();
            assertEquals(i, selectedChar);
        }
    }

    @Test
    public void testGetLetters() {
        Application model = new Application();
        int[] name = model.getName();
        char[] letters = model.getNameCharacters();

        for (int characterIndex : name) {
            assertEquals(0, characterIndex);
            assertEquals('A', letters[characterIndex]);
        }
    }

    @Test
    public void testSetLetters() {
        Application model = new Application();
        char[] letters = model.getNameCharacters();

        for (int y = 0; y < 3; y++) {
            for (int i = 0; i < 26; i++) {
                int[] name = model.getName();
                assertEquals(letters[i], letters[name[y]]);
                model.incrementLetter();
            }
            int[] name = model.getName();
            assertEquals(letters[0], letters[name[y]]);
            model.incrementSelectedChar();
        }
    }

    @Test
    public void testInc() {
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
    public void testDec() {
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
