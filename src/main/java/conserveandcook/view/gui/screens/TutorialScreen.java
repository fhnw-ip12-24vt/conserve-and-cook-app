package conserveandcook.view.gui.screens;

import ch.trick17.gui.Gui;
import conserveandcook.misc.I18n;
import conserveandcook.model.Application;
import conserveandcook.view.gui.AbstractScreen;
import conserveandcook.view.gui.AvailableScreens;
import conserveandcook.view.gui.components.Arrow;
import conserveandcook.view.gui.components.Button;
import conserveandcook.view.gui.components.SkipButton;

public class TutorialScreen extends AbstractScreen {

    private int frameIndex = 0;
    Button skipButton;
    Arrow nextButton;

    public TutorialScreen(Gui gui) {
        super(gui);

        nextButton = new Arrow(Button.States.ACTIVE,
                Arrow.Direction.RIGHT,
                (int) (1200 * SCALE),
                (int) (880 * SCALE)
        );
        skipButton = new SkipButton(
                Button.States.IDLE,
                (int) (130 * SCALE),
                (int) (880 * SCALE)
        );
    }

    @Override
    public void draw(Application model) {
        drawBackground("img/tutorial/frame_" + frameIndex + ".png");

        Result coordinates = getCoordinates();
        gui.setTextAlignCenter();
        gui.setFontSize((int) (coordinates.fontsize() * SCALE));
        gui.drawString(getText(), coordinates.x() * SCALE, (coordinates.y()) * SCALE);

        skipButton.draw(gui);
        nextButton.draw(gui);
    }

    @Override
    public void init(Application model) {
        frameIndex = 0;
        skipButton.setLanguage(model.getSelectedLanguage());
        skipButton.setOnPress(model::skipTutorial);
        skipButton.setState(Button.States.IDLE);
        nextButton.setOnPress(model::incrementScreen);
        nextButton.setState(Button.States.ACTIVE);
    }

    @Override
    public AvailableScreens next() {
        frameIndex++;
        if (frameIndex > 5) {
            frameIndex = 0;
            return AvailableScreens.REGION;
        }
        return null;
    }

    @Override
    public void press(Application model) {
        if (nextButton.isActive()) {
            nextButton.onPress(gui);
        } else if (skipButton.isActive()) {
            skipButton.onPress(gui);
        }
    }

    @Override
    public AvailableScreens prev() {
        return AvailableScreens.LANGUAGE;
    }

    @Override
    public void left(Application model) {
        skipButton.setState(Button.States.ACTIVE);
        nextButton.setState(Button.States.IDLE);
    }

    @Override
    public void right(Application model) {
        nextButton.setState(Button.States.ACTIVE);
        skipButton.setState(Button.States.IDLE);
    }

    // TODO: (SK) Replace this function with i18n results
    private String getText() {
        String key = "tutorial." + switch (frameIndex) {
            case 0 -> "intro";
            case 1 -> "aim";
            case 2 -> "region";
            case 3 -> "ingredient";
            case 4 -> "scan";
            case 5 -> "end";
            default -> "";
        };

        return I18n.translate(key);
    }

    private record Result(int x, int y, int fontsize) {
    }

    private Result getCoordinates() {
        int x, y, fontsize = 65;

        switch (frameIndex) {
            case 0:
                x = 670;
                y = 365;
                fontsize = 75;
                break;

            case 1:
                x = 655;
                y = 340;
                fontsize = 70;
                break;

            case 2:
                x = 1115;
                y = 680;
                fontsize = 50;
                break;

            case 3:
                x = 1025;
                y = 180;
                fontsize = 50;
                break;

            case 4:
                x = 1000;
                y = 180;
                fontsize = 50;
                break;

            case 5:
                x = 735;
                y = 450;
                fontsize = 80;
                break;

            default:
                x = 100;
                y = 100;
                break;
        }
        return new Result(x, y, fontsize);
    }
}
