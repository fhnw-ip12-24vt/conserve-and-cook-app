package conserveandcook.view.gui.components;

import ch.trick17.gui.Gui;

import static conserveandcook.view.gui.AbstractScreen.SCALE;

public class FinishButton extends Button {
    public FinishButton(States state, int x, int y) {
        super(Type.FINISH, x, y);
        super.setState(state);
    }

    @Override
    public void draw(Gui gui) {
        String path = "img/buttons/finish/";

        path += getState().name().toLowerCase() + "/";

        if (getLanguage() == null) throw new UnsupportedOperationException("Language has to be set");
        path += getLanguage().name().toLowerCase();

        gui.drawImage(path + ".png", super.getX(), super.getY(), SCALE);
    }
}
