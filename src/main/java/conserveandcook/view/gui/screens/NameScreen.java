package conserveandcook.view.gui.screens;

import ch.trick17.gui.Gui;
import conserveandcook.model.Application;
import conserveandcook.view.gui.AvailableScreens;

public class NameScreen extends AbstractScreen {
    char[] characters;

    public NameScreen(Gui gui) {
        super(gui);
    }

    @Override
    public void draw(Application model) {
        String backgroundPath = "img/name/";
        String path = backgroundPath + "background.png";
        characters = model.getNameCharacters();

        drawBackground(path);

        int[] name = model.getName();
        for (int i = 0; i < name.length; i++) {
            drawLetter(i, characters[name[i]]);
        }
    }

    private void drawLetter(int index, char letter) {
        int fontSize = 140;
        int x = 530;
        int y = 675;

        int nextLetter = 350 * index;

        gui.setFontSize(fontSize);
        gui.drawString(String.valueOf(letter), (x + nextLetter) * SCALE, y * SCALE);
    }

    @Override
    public AvailableScreens next() {
        return AvailableScreens.RESULT;
    }

    @Override
    public void left(Application model) {
        model.decrementSelectedChar();
    }

    @Override
    public void right(Application model) {
        model.incrementSelectedChar();
    }

    @Override
    public void up(Application model) {
        model.decrementLetter();
    }

    @Override
    public void down(Application model) {
        model.incrementLetter();
    }
}
