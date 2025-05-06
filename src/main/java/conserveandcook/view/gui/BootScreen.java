package conserveandcook.view.gui;

import ch.mvcbase.GuiBase;
import ch.trick17.gui.widget.Button;
import conserveandcook.controller.BootController;
import conserveandcook.model.Boot;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class BootScreen extends GuiBase<Boot, BootController> {

    protected final BootController controller;

    private Button bootAppButton;
    private Button resetHighScoresButton; // NN added -> High Score Reset button
    private Timer autoOpenTimer;// NN added -> 5s timer
    private Timer countdownTimer; // NN added -> 15s timer

    private int countdown = 5; //NN added -> Start countdown from 5 seconds

    public BootScreen(BootController controller) {
        super(controller, "Conserve & Cook | Boot Screen", 800, 400);
        this.controller = controller;

        this.setResizable(true);

        new Thread(() -> controller.boot()).start(); // NN added -> Start boot() automatically when the BootScreen opens

        // NN added -> Auto-open timer
        autoOpenTimer = new Timer();
        autoOpenTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                Boot model = controller.getModel();
                if (model != null && model.isSuccess()) {
                    try {
                        model.log("Auto-opening application after 15 seconds");
                        model.openApplication();
                    } catch (Exception e) {
                        model.log("Auto-open failed: " + e.getMessage());
                    }
                } else {
                    if (model != null) {
                        model.log("Auto-open skipped: Boot not successful");
                    }
                }
            }
        }, 5000);

        // NN added -> Countdown timer that updates every second
        countdownTimer = new Timer();
        countdownTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (countdown > 0) {
                    countdown--;
                    updateComponents(controller.getModel()); // trigger redraw to update countdown display
                } else {
                    countdownTimer.cancel();
                }
            }
        }, 0, 1000);
    }

    @Override
    protected void redraw(Boot model) {
        List<String> logs = model.getLogs();
        for (int i = 0; i < logs.size(); i++) {
            int x = 10;
            int y = 20 + (i * 20);

            this.setFontSize(20);
            this.drawString(logs.get(i), x, y);
        }

        // NN added -> Display countdown timer
        this.setFontSize(24);
        this.drawString("Auto-starting in: " + countdown + "s", 10, 330);

        if (!model.isSuccess()) {
            this.setFontSize(20);
            this.drawString("!!! FEHLER: Boot fehlgeschlagen !!!", 10, 350);
        }

        // NN added -> Refresh click behavior in case it's updated
        bootAppButton.setOnClick((Double x, Double y) -> {
            if (!model.isSuccess()) return;
            try {
                model.log("Trying to open application");
                model.openApplication();
            } catch (Exception e) {
                model.log(e.getMessage());
            }
        });
    }

    @Override
    public void initializeComponents(Boot model) {
        bootAppButton = new Button("Boot App", false, 10, 250, 160, 50);
        bootAppButton.setOnClick((Double x, Double y) -> {
            try {
                model.log("Trying to open application");
                model.openApplication();
            } catch (Exception e) {
                model.log(e.getMessage());
            }
        });

        // NN added -> High Score Rest
        resetHighScoresButton = new Button("Reset High Scores", false, 200, 250, 250, 50);
        resetHighScoresButton.setOnClick((Double x, Double y) -> {
            // No functionality yet
        });

        this.addComponents(bootAppButton, resetHighScoresButton);  // NN added -> High Score Rest
    }
}