package conserveandcook.view.gui.components;

import ch.trick17.gui.Gui;
import conserveandcook.misc.Languages;

import static conserveandcook.view.gui.AbstractScreen.SCALE;

public class Button implements Component {
    // State is mutable
    private States state;
    private Direction direction;
    private final Type type;
    private Languages languages;

    // Buttons don't move
    private int x, y;

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
        String path = "img/buttons/";

        path += state.name().toLowerCase();
        path +="_";
        path += type.name().toLowerCase();

        if (languages != null){
            path += languages.name();
        }
       if (direction  != null){
            path += direction.name();
        }

        System.out.println(path + ".png");
        if(type != Type.NO_GUI) {
            gui.drawImage(path + ".png", x, y, SCALE);
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

    public void setLanguage(Languages languages) {
        this.languages = languages;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Runnable getOnClick() {
        return onClick;
    }

    public void setOnClick(Runnable onClick) {
        this.onClick = onClick;
    }

    public States getState() {
        return state;
    }

    public enum Type {
        ARROW, BUTTON, NO_GUI, CONFIRM, FINISH, SKIP
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
