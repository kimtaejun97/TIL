package baseball.domain;

import java.util.HashSet;
import java.util.List;

public class Balls {

    private final List<Integer> numbers;
    private static final int BALL_SIZE = 3;


    public Balls(List<Integer> numbers) {
        this.numbers = numbers;
        validateDuplicatedNumber(numbers);
        validateNumberRange(numbers);
    }

    private void validateDuplicatedNumber(List<Integer> numbers){
        if(new HashSet<>(numbers).size() != BALL_SIZE){
            throw new IllegalArgumentException("[Error] 중복되지 않은 3자리 숫자를 입력해 주세요.");
        }
    }

    private void validateNumberRange(List<Integer> numbers) {
        for(Integer number : numbers){
            if(number < 1 || number > 9){
                throw new IllegalArgumentException("[Error] 1부터 9 사이의 숫자를 입력해 주세요.");
            }
        }
    }

    public List<Integer> getNumbers() {
        return numbers;
    }
}