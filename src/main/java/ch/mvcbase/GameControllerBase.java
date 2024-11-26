package ch.mvcbase;

public abstract class GameControllerBase<M> extends ControllerBase<M> {

    protected GameControllerBase(M model) {
        super(model);
    }

    /**
     * Is called inside the gaming loop.
     *
     */
    public abstract void step();
}
