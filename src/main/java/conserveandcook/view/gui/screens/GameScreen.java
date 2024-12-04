package conserveandcook.view.gui.screens;

import java.util.Timer;
import java.util.TimerTask;

public class GameScreen implements Screen {
    private int currentFrame = 0;
    private Timer gametimer;
    private int timeremaining = 120; //Spielzeit in Sekunden

    @Override
    public String getTitle() {
        return "game";
    }

    public String getCurrentFrame(String language) {
        String backgroundPath = "img/game";
        String frameName = String.format("%01d", currentFrame);
        return backgroundPath + "/frame_" + frameName + ".png";
    }

    public void startGameTimer() {
        gametimer = new Timer();
        gametimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                timeremaining--;
                updateTimerDisplay();
                if (timeremaining <= 0) {
                    gametimer.cancel();
                    endGame();
                }
            }
        }
    }

    private void updateTimerDisplay() { //Anzeige von 60 Sekunden in 1 Minuten
        int minutes = timeremaining / 60;
        int seconds = timeremaining % 60;
        String formatierteZeit = ; // Zeit in String formatieren
        System.out.println(formatierteZeit); //Hier könnte die Anbindung ins GUI gemacht werden
    }

    private void endGame() {
        System.out.println("GAME OVER");
    }
}



