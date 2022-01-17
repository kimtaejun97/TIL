package baseball.domain;

import java.util.List;

public class Judgement {

    public String compare(Balls computer, Balls player){
        List<Integer> computerNumbers=  computer.getNumbers();
        List<Integer> playerNumbers=  player.getNumbers();

        return null;
    }

    private int correctCount(List<Integer> computer, List<Integer> player){
        int count = 0;
        for(Integer number : player){
            if (computer.contains(player)){
                count++;
            }
        }
        return count;
    }

}
