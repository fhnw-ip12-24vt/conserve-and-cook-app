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
        drawText("Willkommen bei Conserve&Cook!");
    }

    private void drawText(String text) {
        gui.setTextAlignCenter();
        Result coordinates = getCoorinates();
        gui.setFontSize((int) (coordinates.fontsize() * SCALE));
        gui.drawString(text, coordinates.x() * SCALE, coordinates.y() * SCALE);
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


    private record Result(int x, int y, int fontsize) {
    }

    private Result getCoorinates() {
        int x, y;
        int fontsize = 65;

        switch (frameIndex) {
            case 0:
                x = 645;
                y = 345;
                break;

            case 1:
                x = 665;
                y = 330;
                break;

            case 2:
                x = 1170;
                y = 660;
                fontsize = 40;
                break;

            case 3:
                x = 1025;
                y = 260;
                fontsize = 40;
                break;

            case 4:
                x = 975;
                y = 245;
                fontsize = 50;
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
        return new Result(x, y, fontsize);
    }
}
