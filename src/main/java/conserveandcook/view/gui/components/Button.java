package conserveandcook.view.gui.components;

import ch.trick17.gui.Gui;

import static conserveandcook.view.gui.AbstractScreen.SCALE;

public class Button implements Component {
    // State is mutable
    private States state;
    private Direction direction;
    private Type type;

    // Buttons don't move
    private final int x, y;

    private Runnable onClick;

    /**
     * @param state     The initial state of the Button. It can change later via user input.
     * @param direction The direction in which the button points. Up, Down, Left & Right are allowed.
     * @param type      The type of button. Two available: Arrow (small, compact) and Button (big)
     * @param x         The x coordinate
     * @param y         The y coordinate
     */
    public Button(States state, Direction direction, Type type, int x, int y) {
        this.state = state;
        this.direction = direction;
        this.type = type;
        this.x = x;
        this.y = y;
    }

    public Button(Runnable onClick, Type type, int y, int x) {
        this(States.IDLE, null, type, x, y);
        this.onClick = onClick;
    }

    public Button(Runnable onClick) {
        this(States.IDLE, null, Type.NO_GUI, 0, 0);
        this.onClick = onClick;
    }

    public Button(Type type) {
        this(States.IDLE, null, type, 0, 0);
    }

    @Override
    public void draw(Gui gui) {
        String path = "";
        switch (state) {
            case States.IDLE -> path += "idle";
            case States.ACTIVE -> path += "active";
            case States.DISABLED -> path += "disabled";
        }

        path = "img/buttons/active_arrowRbutton.png";

        if(type != Type.NO_GUI) {
            gui.drawImage(path, x, y, SCALE);
        }
    }

    @Override
    public void onPress(Gui gui) {
        if (this.state != States.ACTIVE) return;
        if (onClick != null) {
            onClick.run();
        }
    }

    public void setOnPress(Runnable task) {
        this.onClick = task;
    }

    public void setState(States state) {
        this.state = state;
    }

    public States getState() {
        return state;
    }

    public enum Type {
        ARROW, BUTTON, NO_GUI
    }

    public enum Direction {
        UP, DOWN, LEFT, RIGHT
    }

    public enum States {
        IDLE, ACTIVE, DISABLED
    }

    public void mockPress() {
        if (this.state != States.ACTIVE) return;
        if (onClick != null) {
            onClick.run();
        }
    }
}
