package conserveandcook.view.gui.components;

import ch.trick17.gui.Gui;
import conserveandcook.model.Application;

import java.util.ArrayList;
import java.util.List;

public class ComponentList {
    private final ArrayList<Component> list = new ArrayList<>();
    private int activeIndex = 0;

    public void drawEach(Gui g) {
        list.iterator().forEachRemaining(component -> {
            component.draw(g);
        });
    }

    public void add(Component component) {
        list.add(component);
    }

    public void addMany(List<? extends Component> components) {
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
}
