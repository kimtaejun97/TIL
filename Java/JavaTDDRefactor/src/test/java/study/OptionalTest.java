package study;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import java.util.Optional;
import org.junit.jupiter.api.Test;

public class OptionalTest {

    @Test
    void optional() {
        Optional<String> result = Optional.ofNullable("abc");

        assertThat(result.get()).isEqualTo("abc");
    }

    @Test
    void optional_null() {
        Optional<String> result = Optional.ofNullable(null);

        assertThat(result.orElse("null~!")).isEqualTo("null~!");
    }

    @Test
    void optional_null_throw() {
        Optional<String> result = Optional.ofNullable(null);

        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> result.orElseThrow(IllegalArgumentException::new));
    }
}
