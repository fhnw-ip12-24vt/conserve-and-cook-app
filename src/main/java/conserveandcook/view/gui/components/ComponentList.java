package conserveandcook.view.gui.components;

import ch.trick17.gui.Gui;
import conserveandcook.model.Application;

import java.util.ArrayList;
import java.util.List;

public class ComponentList {
    private final ArrayList<Button> list = new ArrayList<>();
    private int activeIndex = 0;
    private final Gui gui;

    public ComponentList(Gui g) {
        this.gui = g;
    }

    public void drawEach(Gui g) {
        ensureActive();
        list.iterator().forEachRemaining(component -> {
            component.draw(g);
        });
    }

    public void add(Button component) {
        list.add(component);
    }

    public void addMany(List<Button> components) {
        list.addAll(components);
    }

    public void incrementActiveIndex() {
        this.activeIndex = Application.incrementWrapped(activeIndex, list.size() - 1);
    }

    public void decrementActiveIndex() {
        this.activeIndex = Application.decrementWrapped(activeIndex, list.size() - 1);
    }

    public int size() {
        return list.size();
    }

    public void press() {
        list.get(activeIndex).onPress(gui);
    }

    private void ensureActive() {
        if(list.isEmpty()) return;
        Button activeButton = list.get(activeIndex);

        if (activeButton == null) return;
        activeButton.setState(Button.States.ACTIVE);
    }
}
