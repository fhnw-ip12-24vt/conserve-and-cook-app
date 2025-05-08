package conserveandcook.view.pui.components;

import com.pi4j.catalog.components.base.Component;

public class Button extends Component {
    private Runnable onPress;

    public void setPressed(boolean isPressed) {
        if (isPressed && onPress != null) {
            onPress.run();
        }
    }

    public void onPress(Runnable task) {
        this.onPress = task;
    }
}
