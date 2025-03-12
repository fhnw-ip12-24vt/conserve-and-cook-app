package conserveandcook.misc;

import conserveandcook.AbstractTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EnvironmentTest extends AbstractTest {
    @Test
    public void testGetEnv(){
        Environments currentEnv = Environments.get();
        assertEquals(Environments.TEST, currentEnv);
    }
}
