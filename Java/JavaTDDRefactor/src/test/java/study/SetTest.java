package study;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;
import org.junit.jupiter.api.Test;

public class SetTest {

    @Test
    void test(){
        Set<Object> set = new HashSet<>(Arrays.asList(2000, 22, 100));
        assertThat(set).containsExactly(2000, 100, 22);

        Set<Integer> set2 = new TreeSet<>(Arrays.asList(-1,1,0));
        assertThat(set2).containsExactly(-1,0,1);
    }

}
