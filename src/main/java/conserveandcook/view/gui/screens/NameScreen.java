package conserveandcook.view.gui.screens;

import ch.trick17.gui.Gui;
import conserveandcook.model.Application;

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
}
