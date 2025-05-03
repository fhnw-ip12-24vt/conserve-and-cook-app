package conserveandcook.controller;

import ch.mvcbase.ControllerBase;
import conserveandcook.checks.Check;
import conserveandcook.checks.CheckException;
import conserveandcook.checks.DatabaseCheck;
import conserveandcook.checks.ImageCheck;
import conserveandcook.model.Boot;

import java.util.Stack;

public class BootController extends ControllerBase<Boot> {
    private final Stack<Check> checks = new Stack<>();

    /**
     * @param model Model managed by this Controller
     */
    public BootController(Boot model) {
        super(model);
        checks.push(new ImageCheck());
        checks.push(new DatabaseCheck());

    }

    public void boot(){
        boolean result = runChecks();
        model.setSuccess(result);
        if (result) {
            model.log("Boot checks successful, you can now open the application");
//            model.openApplication();
        } else {
            model.log("Boot checks failed");
        }

        // TODO: (NN) Das Fenster für das Spiel soll sich nur öffnen, wenn alle Checks erfolgreich sind.
    }

    /**
     * Run the checks in the order of the stack
     * @return true if all checks ran successfully and false if not
     */
    public boolean runChecks() {
        while (!checks.isEmpty()) {
            Check check = checks.pop();
            boolean result = false;

            try {
                result = check.run();
            } catch (CheckException e) {
                model.log(check.getClass().getSimpleName() +" failed: " + e.getMessage());
                break;
            }

            if (result) {
                model.log(check.getClass().getSimpleName() + " successful");
            }
        }

        return checks.isEmpty();
    }

}
