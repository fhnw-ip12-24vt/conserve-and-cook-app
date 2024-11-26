package ch.mvcbase;

import javax.swing.*;
import java.lang.reflect.InvocationTargetException;
import java.util.Objects;
import java.util.function.Consumer;

import ch.trick17.gui.impl.swing.Window;

public abstract class GuiBase<M, C extends ControllerBase<M>>  extends Window implements Projector<M, C> {

    private final C controller;

    private Consumer<String> keyReleasedHandler = key -> {};
    private Consumer<String> keyPressedHandler  = key -> {};

    public GuiBase(C controller, String title, int width, int height) {
        super(title, width, height);
        this.controller = controller;
        init(controller);
    }

    protected final void setOnKeyReleased(Consumer<String> keyHandler){
        Objects.requireNonNull(keyHandler);
        keyReleasedHandler = keyHandler;
    }

    protected final void setWhileKeyPressed(Consumer<String> keyHandler){
        Objects.requireNonNull(keyHandler);
        keyPressedHandler = keyHandler;
    }

    @Override
    protected final void repaint(boolean clear) {
        if (!getTypedKeys().isEmpty()) {
            for (String key : getTypedKeys()) {
                keyReleasedHandler.accept(key);
            }
        }
        if (!getPressedKeys().isEmpty()) {
            for (String key : getPressedKeys()) {
                keyPressedHandler.accept(key);
            }
        }
        performNextStep(controller);
        super.repaint(clear);
        redraw(controller.model);
    }

    protected abstract void redraw(M model);

    protected void performNextStep(C controller){
        updateComponents(controller.model);
    }
}
