package baseball.domain;

import java.util.List;

public class Judgement {

    public String compare(Balls computer, Balls player){
        List<Integer> computerNumbers=  computer.getNumbers();
        List<Integer> playerNumbers=  player.getNumbers();

        int correctCount = correctCount(computerNumbers, playerNumbers);
        if(correctCount == 0){
            return "낫싱";
        }

        int strike = countStrike(computerNumbers, playerNumbers);
        int ball = correctCount - strike;

        return strike + "스트라이크 " + ball + "볼";
    }

    private int correctCount(List<Integer> computer, List<Integer> player){
        int count = 0;
        for(Integer number : player){
            if (computer.contains(number)){
                count++;
            }
        }
        return count;
    }

    private int countStrike(List<Integer> computer, List<Integer> player){
        int count = 0;
        for (int i = 0; i < computer.size(); i++) {
            if(hasPlace(computer, i, player.get(i))){
                count ++;
            }
        }
        return count;
    }

    private boolean hasPlace(List<Integer> computer, int idx, int number){
        return computer.get(idx) == number;

    }

}
