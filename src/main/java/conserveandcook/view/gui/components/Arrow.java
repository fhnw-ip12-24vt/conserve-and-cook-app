package conserveandcook.view.gui.components;

import ch.trick17.gui.Gui;

import static conserveandcook.view.gui.AbstractScreen.SCALE;

public class Arrow extends Button {
    private Direction direction;
    public Arrow(States state, Direction direction, int x, int y) {
        super(Type.CONFIRM, x, y);
        super.setState(state);
        setDirection(direction);
    }

    @Override
    public void draw(Gui gui) {
        String path = "img/buttons/arrow/";
        path += getState().name().toLowerCase() + "/";
        path += getDirection().name().toLowerCase();

        gui.drawImage(path + ".png", super.getX(), super.getY(), SCALE);
    }

    public enum Direction {
        UP, DOWN, LEFT, RIGHT
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }
}
