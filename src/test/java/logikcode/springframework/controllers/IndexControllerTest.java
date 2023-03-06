package logikcode.springframework.controllers;

import logikcode.springframework.petty.controllers.IndexController;
import logikcode.springframework.petty.controllers.ValueNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

class IndexControllerTest {
    IndexController controller;
    @BeforeEach
    void setUp() {
    controller = new IndexController();
    }

    @Test
    void index() {
        assertEquals("index", controller.index());
        assertEquals("index", controller.index(), "Wrong view returned");
    }

    @Test
    @DisplayName("Test proper view is returned")
    void oupsHandler() {
        assertEquals("something", controller.oupsHandler(),()-> "taking in expression to execute on failure" );
    }
    @Test
    @DisplayName("Test Thrown Exception")
    void testThrownException(){
        assertThrows(ValueNotFoundException.class, ()->{
            controller.exThrower();
        });
    }

    @Test
    @Disabled(value = "Demo timeout")
    void testTimeout(){
        assertTimeout(Duration.ofMillis(300), ()->{
            Thread.sleep(5000);
            System.out.println("I got here");
        });
    }

    @Test
    @Disabled(value = "Demo timeout")
    void testTimeoutPreempt(){
        assertTimeoutPreemptively(Duration.ofMillis(300), ()->{
            Thread.sleep(5000);
            System.out.println("I got here inside the preempt");
        });
    }

    @Test
    void testAssumption(){
    }
}