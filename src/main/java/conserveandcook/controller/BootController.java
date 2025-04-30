package conserveandcook.controller;

import ch.mvcbase.ControllerBase;
import conserveandcook.checks.Check;
import conserveandcook.checks.CheckException;
import conserveandcook.checks.DatabaseCheck;
import conserveandcook.checks.ImageCheck;
import conserveandcook.model.Boot;

import java.util.Stack;

public class BootController extends ControllerBase<Boot> {
    private Stack<Check> checks = new Stack<>();

    /**
     * @param model Model managed by this Controller
     */
    public BootController(Boot model) {
        super(model);
        checks.push(new ImageCheck());
        checks.push(new DatabaseCheck());

        runChecks();
    }

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
                model.log("Check Passed: "+ check.getClass().getSimpleName());
            }
        }

        return checks.isEmpty();
    }

}
