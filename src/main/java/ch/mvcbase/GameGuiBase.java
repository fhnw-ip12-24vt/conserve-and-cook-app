package ch.mvcbase;


public abstract class GameGuiBase <M, C extends GameControllerBase<M>>  extends GuiBase<M, C> {

    public GameGuiBase(C controller, String title, int width, int height) {
        super(controller, title, width, height);
    }

    @Override
    public final void initializeComponents(M model) {
        // there are no components in a pure game
    }

    @Override
    protected final void performNextStep(C controller) {
        controller.step();
        super.performNextStep(controller);
    }
}
