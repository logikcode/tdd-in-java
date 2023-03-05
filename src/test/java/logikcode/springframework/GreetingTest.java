package logikcode.springframework;

import logikcode.springframework.junit5.Greeting;
import org.junit.jupiter.api.*;

public class GreetingTest {
    Greeting greeting;
    @BeforeEach
    void setUp(){
    greeting = new Greeting();
    }

    @Test
    void helloWorld(){
        System.out.println(greeting.greets());
    }

    @Test
    void helloWorld1(){
        System.out.println(greeting.greets("coder"));
    }
    @AfterEach
    void tearDown(){
        System.out.println("In After Each ...");
    }
    @BeforeAll
    public static void beforeAll(){
        System.out.println("Only called once...");
    }
    @AfterAll
    public static void afterAll(){
        System.out.println("Only called once after all test...");
    }

    @AfterEach
    public void afterEach(){
        System.out.println("Called for each test...");
    }
}
