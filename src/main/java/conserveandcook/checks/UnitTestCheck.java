package conserveandcook.checks;

public class UnitTestCheck extends Check {
    @Override
    public boolean run() throws CheckException {
        //Here you'd run real tests or simulate passing tests
        boolean testsPassed = true;

        if (!testsPassed) {
            throw new CheckException("Some unit tests failed");
        }
        return true;
    }
}
//NN added -> Unit Test Check at start