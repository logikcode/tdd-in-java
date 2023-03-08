package logikcode.springframework;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.stream.Stream;

public class CustomArgsProvider implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) throws Exception {
        return Stream.of(
                Arguments.of("Dev", "Java", "Backend"),
                Arguments.of("Dev", "React", "Frontend"),
                Arguments.of("Dev", "Angular", "Frontend"));
    }
}
