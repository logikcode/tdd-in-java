package logikcode.springframework.petty.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EnumSource;
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

    @DisplayName("Demonstrating EnumType test")
    @ParameterizedTest(name = "{displayName} - [{index}] {arguments}")
    @EnumSource(OwnerType.class)
    void testingEnumValue(OwnerType ownerType){
        System.out.println(ownerType);
    }
    @DisplayName("Demonstrating Parameterized method")
    @ParameterizedTest(name = "{displayName} - [{index}] {arguments}")
    @CsvSource({"Spring, 6.0, v6", "Framework, 3.1, v3", "Test, Driven, Development"})
    void testingCsvInput(String stateName, String val1, String val2){
        System.out.println(stateName +" "+ val1 + " "+ val2);

    }

}