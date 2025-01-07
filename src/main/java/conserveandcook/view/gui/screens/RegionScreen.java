package conserveandcook.view.gui.screens;

public class RegionScreen implements conserveandcook.view.gui.screens.Screen {

    @Override
    public String getTitle() {
        return "region";
    }

    @Override
    public String getCurrentFrame(String regions) {
        String backgroundPath = "img/regions/";
        return backgroundPath + regions + ".png";
    }
}
