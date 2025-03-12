package conserveandcook.view.gui.screens;

import ch.trick17.gui.Gui;
import conserveandcook.model.Application;
import conserveandcook.view.gui.AbstractScreen;
import conserveandcook.view.gui.AvailableScreens;

public class TutorialScreen extends AbstractScreen {

    private int frameIndex = 0;

    public TutorialScreen(Gui gui) {
        super(gui);
    }

    @Override
    public void draw(Application model) {
        drawBackground("img/tutorial/frame_" + frameIndex + ".png");
        // TODO: (VW) "and" mit "&" ersetzen in der Font
        drawText("01234567 8901 23456789012345678901 2345 67890123 45678901234567890");
    }

    private void drawText(String text) {
        gui.setTextAlignCenter();
        Result coordinates = getCoordinates();
        gui.setFontSize((int) (coordinates.fontsize() * SCALE));

        String[] lines = splitStringByWordLength(text, coordinates.wordLength());

        int yOffset = 0;
        for (String line : lines) {
            gui.drawString(line, coordinates.x() * SCALE, (coordinates.y() + yOffset) * SCALE);
            yOffset += gui.getFontSize() + 20;
        }
    }

    @Override
    public AvailableScreens next() {
        if (frameIndex + 1 > 5) {
            frameIndex = 0;
            return AvailableScreens.REGION;
        }
        frameIndex++;
        return null;
    }

    @Override
    public AvailableScreens prev() {
        frameIndex = Math.max(frameIndex - 1, 0);
        return null;
    }


    private record Result(int x, int y, int fontsize, int wordLength) {
    }

    public String[] splitStringByWordLength(String text, int wordLength) {
        int textLength = text.length();

        if (textLength <= wordLength) {
            return new String[]{text};
        }
        int requiredLines;
        if (textLength % wordLength == 0) {
            requiredLines = textLength / wordLength;
        } else {
            requiredLines = (textLength / wordLength) + 1;
        }
        String[] lines = new String[requiredLines];
        for (int i = 0; i < requiredLines; i++) {
            String line = text.substring(i, i + wordLength);
            if (line.contains(" ")) {
                line = line.substring(0, line.lastIndexOf(" "));
            }
            lines[i] = line;
        }

        return lines;
    }

    private Result getCoordinates() {
        int x, y, wordLength = 30;
        int fontsize = 65;

        switch (frameIndex) {
            case 0:
                x = 650;
                y = 330;
                break;

            case 1:
                x = 655;
                y = 330;
                wordLength = 32;
                break;

            case 2:
                x = 1170;
                y = 660;
                fontsize = 45;
                break;

            case 3:
                x = 1025;
                y = 260;
                fontsize = 45;
                wordLength = 35;
                break;

            case 4:
                x = 975;
                y = 245;
                fontsize = 50;
                wordLength = 35;
                break;

            case 5:
                x = 735;
                y = 350;
                fontsize = 50;
                break;

            default:
                x = 100;
                y = 100;
                break;
        }
        return new Result(x, y, fontsize, wordLength);
    }
}
