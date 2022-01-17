package baseball;


import baseball.domain.Balls;
import baseball.domain.Judgement;
import baseball.domain.NumberGenerator;
import java.util.Arrays;
import java.util.List;

public class Application {

    public static void main(String[] args) {
        NumberGenerator numberGenerator = new NumberGenerator();
        List<Integer> randomNumber = numberGenerator.createRandomNumber();
        System.out.println(randomNumber);

        Balls computer = new Balls(randomNumber);
        Balls player = new Balls(Arrays.asList(3,2,7));

        Judgement judgement = new Judgement();
        System.out.println(judgement.compare(computer, player));
    }
}
