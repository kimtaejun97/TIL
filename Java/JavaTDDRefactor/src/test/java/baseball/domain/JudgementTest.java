package baseball.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class JudgementTest {

    Judgement judgement;
    Balls computer;

    @BeforeEach
    void setup(){
        judgement = new Judgement();
        computer = new Balls(Arrays.asList(1, 2, 3));
    }

    @Test
    void 낫싱() {

        // given
        Balls player = new Balls((Arrays.asList(4,5,6)));

        // when
        String result = judgement.compare(computer, player);

        // then
        assertThat(result).isEqualTo("낫싱");
    }

    @Test
    void 원볼(){

        // given
        Balls player = new Balls((Arrays.asList(4, 1, 6)));

        // when
        String result = judgement.compare(computer, player);

        // then
        assertThat(result).isEqualTo("0스트라이크 1볼");
    }

    @Test
    void 원스트라이크(){

        // given
        Balls player = new Balls((Arrays.asList(1, 5, 6)));

        // when
        String result = judgement.compare(computer, player);

        // then
        assertThat(result).isEqualTo("1스트라이크 0볼");
    }

    @ValueSource(ints = {4,5,6,7,8,9})
    @ParameterizedTest
    void 원볼_원스트라이크(int num){

        // given
        Balls player = new Balls((Arrays.asList(1, num, 2)));

        // when
        String result = judgement.compare(computer, player);

        // then
        assertThat(result).isEqualTo("1스트라이크 1볼");
    }

    @Test
    void 쓰리스트라이크(){

        // given
        Balls player = new Balls((Arrays.asList(1, 2, 3)));

        // when
        String result = judgement.compare(computer, player);

        // then
        assertThat(result).isEqualTo("3스트라이크 0볼");
    }
}