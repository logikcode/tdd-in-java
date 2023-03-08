package logikcode.springframework.petty.model;

import logikcode.springframework.CustomArgsProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.util.stream.Stream;

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
    @DisplayName("Demonstrating CSV Test")
    @ParameterizedTest(name = "{displayName} - [{index}] {arguments}")
    @CsvSource({"Spring, 6.0, v6", "Framework, 3.1, v3", "Test, Driven, Development"})
    void testingCsvInput(String stateName, String val1, String val2){
        System.out.println(stateName +" "+ val1 + " "+ val2);

    }
    @DisplayName("Demonstrating CsvFileSource Reading Test")
    @ParameterizedTest(name = "{displayName} - [{index}] {arguments}")
    @CsvFileSource(resources = "/file.csv", numLinesToSkip = 0)
    void testCsvFileSource(String stateName, String version, String frameworkVersion){
        System.out.println(stateName +" "+ version + " "+ frameworkVersion);
    }

    @DisplayName("Method Provider Test")
    @ParameterizedTest(name = "{displayName} - [{index}] {arguments}")
    @MethodSource("getArgs")
    void fromMethodTest(String dev, String lang, String expertise){
        System.out.println(dev + " "+ lang +" "+ expertise);
    }

    static Stream<Arguments> getArgs(){
        return Stream.of(
                Arguments.of("Dev", "Java", "Backend"),
                Arguments.of("Dev", "React", "Frontend"),
                Arguments.of("Dev", "Angular", "Frontend")
        );
    }


    @DisplayName("Method CustomProvider Test")
    @ParameterizedTest(name = "{displayName} - [{index}] {arguments}")
    @ArgumentsSource(CustomArgsProvider.class)
    void fromCustomProviderTest(String dev, String lang, String expertise){
        System.out.println(dev + " "+ lang +" "+ expertise);
    }
}