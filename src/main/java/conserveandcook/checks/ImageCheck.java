package conserveandcook.checks;

import conserveandcook.Database;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.ResultSet;

public class ImageCheck extends Check {
    Database db = Database.getInstance();

    @Override
    public boolean run() throws CheckException {
        checkIngredients();
        checkRecipes();
        return true;
    }

    private boolean checkIngredients() {
        ResultSet result = db.executeQuery("SELECT * from ingredient");
        if (result == null) return false;

        try {
            while (result.next()) {
                String name = result.getString(2);
                String path = "src/main/resources/img/ingredient/" + name + ".png";
                File image = new File(path);
                if (!image.exists()) throw new FileNotFoundException("Ingredient " + path + " not found");
            }

        } catch (Exception e) {
            throw new CheckException(e.getMessage());
        }
        return true;
    }

    private boolean checkRecipes() {
        ResultSet result = db.executeQuery("SELECT * from recipe");
        if (result == null) return false;

        try {
            while (result.next()) {
                String name = result.getString(3);
                String path = "src/main/resources/img/recipe/" + name + ".png";
                File image = new File(path);
                if (!image.exists()) throw new FileNotFoundException("Recipe " + path + " not found");
            }

        } catch (Exception e) {
            throw new CheckException(e.getMessage());
        }
        return true;
    }
}
