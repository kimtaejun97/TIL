package study;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class StreamTest {

    private List<Integer> numbers;

    @BeforeEach
    void setUp() {
        numbers = Arrays.asList(1, 2, 3, 4, 5, 6);
    }

    @Test
    void sumAll() {
        final int result = numbers.stream()
                .mapToInt(n -> n)
                .sum();

        assertThat(result).isEqualTo(21);
    }

    @Test
    void sumAllEven() {
        final int result = numbers.stream()
                .filter(n -> n % 2 == 0)
                .mapToInt(n -> n)
                .sum();

        assertThat(result).isEqualTo(12);
    }

    @Test
    void sumAllOverThree() {
        final int result = numbers.stream()
                .filter(n -> n > 3)
                .mapToInt(n -> n)
                .sum();

        assertThat(result).isEqualTo(15);
    }

    @Test
    void sumOverThreeAndDouble() {
        final int result = numbers.stream()
                .filter(n -> n > 3)
                .mapToInt(n -> n * 2)
                .sum();

        assertThat(result).isEqualTo(30);
    }

    @Test
    void mapToDouble() {
        final List<Integer> actual = numbers.stream()
                .map(n -> n * 2)
                .collect(Collectors.toList());

        assertThat(actual).containsExactly(2, 4, 6, 8, 10, 12);
    }
}
