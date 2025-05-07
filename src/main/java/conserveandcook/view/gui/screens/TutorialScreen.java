package conserveandcook.view.gui.screens;

import ch.trick17.gui.Gui;
import conserveandcook.model.Application;
import conserveandcook.view.gui.AbstractScreen;
import conserveandcook.view.gui.AvailableScreens;
import conserveandcook.view.gui.Window;
import conserveandcook.view.gui.components.Button;

public class TutorialScreen extends AbstractScreen {

    private int frameIndex = 0;
    Button nextButton;
    Button skipButton;
    public TutorialScreen(Gui gui) {
        super(gui);
       nextButton = new Button(Button.Type.ARROW);
        nextButton.setDirection(Button.Direction.RIGHT);
        nextButton.setState(Button.States.ACTIVE);
        nextButton.setY((int) (880 * SCALE));
        nextButton.setX((int) (1200 * SCALE));
        components.add(nextButton);

        skipButton = new Button(Button.Type.SKIP);
        skipButton.setState(Button.States.ACTIVE);
        skipButton.setY((int) (880 * SCALE));
        skipButton.setX((int) (130 * SCALE));

        components.add(skipButton);
        components.incrementActiveIndex();
    }

    @Override
    public void draw(Application model) {
        skipButton.setLanguage(model.getSelectedLanguage());
        drawBackground("img/tutorial/frame_" + frameIndex + ".png");

        skipButton.setOnPress(model::skipTutorial);

        Result coordinates = getCoordinates();
        gui.setTextAlignCenter();
        gui.setFontSize((int) (coordinates.fontsize() * SCALE));
        gui.drawString(getText(), coordinates.x() * SCALE, (coordinates.y()) * SCALE);
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
        return AvailableScreens.REGION;
    }

    // TODO: (SK) Replace this function with i18n results
    private String getText() {
        return switch (frameIndex) {
            case 0 -> "Willkommen bei \nConserve & Cook";
            case 1 -> "Dein Ziel ist es, \nleckere Gerichte \naus aller Welt zu kochen";
            case 2 -> "Die Region bestimmt, \nwelche Rezepte du kochen wirst!";
            case 3 -> "Wähle 3 Zutaten. \n Du Bekommst neun zur Auswahl" +
                    "\n und musst drei davon einscannen, \n" +
                    "bevor die Zeit abläuft.";
            case 4 -> "Scanne die Zutaten. Nutze den Barcodescanner,\n um Zutaten aus dem Kochbuch auszuwählen. \n" +
                    "Genau wie an der Supermarktkasse! \n \n Falls du eine andere Zutat möchtest,\n scanne einfach eine neue ein";
            case 5 -> "Das war's!\nViel Spass beim Kochen!";
            default -> "";
        };
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
