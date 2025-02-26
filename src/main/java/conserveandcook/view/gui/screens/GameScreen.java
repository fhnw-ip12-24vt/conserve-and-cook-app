package conserveandcook.view.gui.screens;

import ch.trick17.gui.Gui;
import conserveandcook.model.Application;
import conserveandcook.model.Ingredient;
import conserveandcook.model.Recipe;
import conserveandcook.view.gui.AvailableScreens;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GameScreen extends AbstractScreen {
    private static final Logger log = LoggerFactory.getLogger(GameScreen.class);

    // TODO: (SK) Remove temporary variable
    private int tmp;

    public GameScreen(Gui g) {
        super(g);
    }

    public void draw(Application model) {
        drawBackground("img/game/frame_0.png");

        Ingredient[] ingredients = model.getSelectedIngredients();
        for (Ingredient ingredient : ingredients) {
            if (ingredient != null) {
                drawBorder(ingredient.getCategory());
            }
        }

    }

    @Override
    public void scan(Application model, String barcode) {
        try {
            Ingredient scannedIngredient = Ingredient.getIngredientById(barcode);
            model.addSelectedIngredient(scannedIngredient);
        } catch (Exception e) {
            log.info(e.getMessage());
        }
        // TODO: (SK) Implement game logic
    }

    @Override
    public void up(Application model) {
        try {
            model.setSelectedRecipe(Recipe.getRandomRecipe());
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    @Override
    public void down(Application model) {
        // TODO: (SK) Remove temporary code, replace with Scanner-Code
        if (model.getSelectedRecipe() != null) {
            model.addSelectedIngredient(model.getSelectedRecipe().getIngredients()[tmp]);
            tmp = model.incrementWrapped(tmp,2);
        }
    }

    @Override
    public AvailableScreens next() {
        return AvailableScreens.NAME;
    }

    private void drawBorder(int category) {
        String borderPath = "img/game/border.png";
        double x, y;
        switch (category) {
            case 0:
                x = (double) 690 ;
                y = (double) 175 ;
                break;
            case 1:
                x = (double) 930 ;
                y = (double) 535 ;
                break;
            case 2:
                x = (double) 1210;
                y = (double) 190 ;
                break;
            default:
                return;
        }
        gui.drawImage(borderPath, x * SCALE, y * SCALE, SCALE);
    }
}
