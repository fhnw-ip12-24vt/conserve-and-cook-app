package ch.mvcbase;

import java.time.Duration;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ControllerBaseTest {
    private TestModel      model;
    private TestController controller;

    @BeforeEach
    void setup() {
        model      = new TestModel();
        controller = new TestController();
    }

    @Test
    void testInitialization() {
        //then
        assertSame(model, controller.getModel());
    }

    @Test
    void testAsync() {
        //given
        int initialInt = model.getSomeInt();
        int newInt  = 42;

        // controller is busy for 50 msec
        controller.pauseExecution(Duration.ofMillis(50));

        //when
        controller.setNumber(newInt);  // number will be set as soon as controller is ready (after 50msec)

        //then asyn returns immediately, the value is still unchanged
        assertEquals(initialInt,  model.getSomeInt());

        //when
        controller.awaitCompletion();

        //then
        assertEquals(newInt, model.getSomeInt());
    }

    private static class TestModel {
        private volatile int someInt = 73;

        public int getSomeInt() {
            return someInt;
        }

        void setSomeInt(int someInt) {
            this.someInt = someInt;
        }
    }

    private class TestController extends ControllerBase<TestModel> {
        public TestController() {
            super(ControllerBaseTest.this.model);
        }

        public void setNumber(int newNumber){
            async(() -> model.setSomeInt(newNumber));
        }

    }
}