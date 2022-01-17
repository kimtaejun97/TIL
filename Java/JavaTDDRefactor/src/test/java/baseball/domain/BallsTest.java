package baseball.domain;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BallsTest {

    @DisplayName("세_개의_숫자는_중복될_수_없다")
    @Test
    void constructor() {
        assertThatIllegalArgumentException().isThrownBy(()->
                new Balls(Arrays.asList(1, 1, 1)));

        assertDoesNotThrow(()->new Balls(Arrays.asList(1,2,3)));

    }

    @DisplayName("숫자는 1부터 9까지 범위이다")
    @Test
    void validateNumberRange() {
        assertThrows(IllegalArgumentException.class, ()-> new Balls(Arrays.asList(1,0,9)));
    }
}

