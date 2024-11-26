package ch.mvcbase;


import ch.trick17.gui.component.Component;

public abstract class MixedGuiBase<M, C extends GameControllerBase<M>>  extends GuiBase<M, C> {

    public MixedGuiBase(C controller, String title, int width, int height) {
        super(controller, title, width, height);
    }

    @Override
    public final void initializeComponents(M model) {
        var components = createComponents(model);
        for (var component : components) {
            addComponent(component);
        }
    }

    protected abstract Component[] createComponents(M model);

    @Override
    protected final void performNextStep(C controller) {
        controller.step();
        super.performNextStep(controller);
    }
}
