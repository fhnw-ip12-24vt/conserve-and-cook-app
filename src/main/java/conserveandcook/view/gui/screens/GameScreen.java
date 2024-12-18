package conserveandcook.view.gui.screens;

import ch.trick17.gui.Gui;
import conserveandcook.model.Application;
import conserveandcook.model.Ingredient;

public class GameScreen extends AbstractScreen {

    public GameScreen(Gui g) {
        super(g);
    }

    public void draw(Application model) {
        drawBackground("img/game/frame_0.png");

        Ingredient[] ingredients = model.getSelectedIngredients();
        for (Ingredient ingredient : ingredients) {
            if (ingredient != null) {
                System.out.println(ingredient.getName());
                drawBorder(ingredient.getCategory());
            }
        }

    }

    private void drawBorder(int category) {
        String borderPath = "img/game/border.png";
        double x, y;
        switch (category) {
            case 0:
                x = (double) 690 / 2;
                y = (double) 175 / 2;
                break;
            case 1:
                x = (double) 930 / 2;
                y = (double) 535 / 2;
                break;
            case 2:
                x = (double) 1210 / 2;
                y = (double) 190 / 2;
                break;
            default:
                System.out.println("Invalid category");
                return;
        }
        gui.drawImage(borderPath, x, y, 0.5);
    }
}
