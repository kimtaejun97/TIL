package study;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class StringTest {
    @Test
    void contains(){
        boolean upper = "ABCDEF".contains("A");
        boolean lower = "ABCDEF".contains("a");

        assertThat(upper).isEqualTo(true);
        assertThat(lower).isEqualTo(false);
    }

}
