package logikcode.springframework.petty.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class OwnerTest {

    @BeforeEach
    void setUp() {

    }

    @Test
    void dependentAssert() {
        Owner owner = new Owner(1L, "Boy", "Joey");
        owner.setCity("North");
        owner.setTelephone("0934234949");

        assertAll("Properties Test", () ->
                assertAll("person Properties",
                        () -> assertEquals("Boy", owner.getFirstName(), "First Name match"),
                        () -> assertEquals("Joey", owner.getLastName())
                ),
                ()-> assertAll("Testing Owner Properties", ()-> assertEquals("North", owner.getCity()),
                        ()-> assertEquals("0934234949", owner.getTelephone())
                        )
        );

    }


    @DisplayName("Demonstrating Parameterized method")
    @ParameterizedTest(name = "{displayName} - [{index}] {arguments}")
    @ValueSource(strings = {"Spring", "Framework", "TDD"})
    void testValueSource(String val){
        System.out.println(val);
    }
}