package conserveandcook.misc;

import conserveandcook.AbstractTest;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.params.provider.Arguments.arguments;

public class I18nTest extends AbstractTest {

    @ParameterizedTest
    @MethodSource("keyArguments")
    public void testI18n(String input, String expected, Languages lang) {
        // Arrange
        String result;

        // Act
        I18n.setLanguage(lang);
        result = I18n.translate(input);

        // Assert
        assertEquals(expected, result);
    }

    @ParameterizedTest
    @ValueSource(strings = {"intro", "explanation"})
    public void testNoLanguage(String key){
        // Arrange
        I18n.setLanguage(null);

        // Assert
        assertThrows(NullPointerException.class, () -> I18n.translate(key));
    }

    private static Stream<Arguments> keyArguments() {
        return Stream.of(
                arguments("intro", "Wilkommen zu Conserve&Cook!", Languages.DE),
                arguments("intro", "Bienvenue chez Conserve&Cook!", Languages.FR),
                arguments("intro", "Benvenuti a Conserve&Cook!", Languages.IT),
                arguments("intro", "Welcome to Conserve&Cook!", Languages.EN),
                arguments("", null, Languages.IT),
                arguments("", null, Languages.FR),
                arguments("", null, Languages.EN),
                arguments("", null, Languages.DE)
        );
    }

}