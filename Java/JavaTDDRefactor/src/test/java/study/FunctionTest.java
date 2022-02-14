package study;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import org.junit.jupiter.api.Test;

class FunctionTest {

    @Test
    void printNumber() {
        final List<Integer> numbers = Arrays.asList(1,2,3,4,5,6);

        for(final int number : numbers) {
            System.out.println(number);
        }
    }

    @Test
    void printNumber2() {
        final List<Integer> numbers = Arrays.asList(1,2,3,4,5,6);

        numbers.forEach(new Consumer<Integer>() {
            @Override
            public void accept(Integer number) {
                System.out.println(number);
            }
        });
    }

    @Test
    void printNumber3() {
        final List<Integer> numbers = Arrays.asList(1,2,3,4,5,6);

        numbers.forEach(number -> System.out.println(number));
    }

    @Test
    void printNumber4() {
        final List<Integer> numbers = Arrays.asList(1,2,3,4,5,6);

        numbers.forEach(System.out::println);
    }
}
