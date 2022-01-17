package baseball;


import baseball.domain.Balls;
import baseball.domain.Judgement;
import baseball.domain.NumberGenerator;
import java.util.List;

public class Application {

    public static void main(String[] args) {
        NumberGenerator numberGenerator = new NumberGenerator();
        List<Integer> randomNumber = numberGenerator.createRandomNumber();
        System.out.println(randomNumber);


//
//        Balls computer = new Balls(randomNumber);
//
//        Balls player;
//
//        Judgement judgement = new Judgement();
//        judgement.compare(computer, player);
    }
}
