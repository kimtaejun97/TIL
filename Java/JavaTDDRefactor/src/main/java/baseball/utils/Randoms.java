package baseball.utils;

import java.util.Random;

/**
 * @author Jung YoonSung <ybook2012@gmail.com>, Kim HyeonSik <hsik0225@gmail.com>, Kim JuWon <kjw11077naver@gmail.com>
 * @version 1.0
 * @since 1.0
 */
public class Randoms {

    /**
     * 랜덤값을 만들어주는 java api
     */
    private static final Random RANDOM = new Random();

    private Randoms() {
    }

    /**
     * 범위내의 랜덤한 숫자를 한개를 반환하는 기능을 수행한다. 임의의 int 를 반환한다.
     * <p>
     * 시작값에서 끝값 사이의 숫자 한개를 랜덤하게 생성하여 반환한다. 예를 들어 Randoms.pick(1, 5) 메소드를 호출할 경우, 1 이상 5 이하의 랜덤한 숫자가 반환된다.
     * </p>
     *
     * @param startInclusive 범위의 시작값, 리턴 범위내에 포함된다.
     * @param endInclusive   범위의 끝값, 리턴 범위내에 포함된다.
     * @return 지정한 범위 안의 랜덤 숫자
     * @throws IllegalArgumentException 스택오버플로우가 터질 수 있는 경우, 발생한다. 잘못된 범위가 입력되는 경우, 발생한다.
     */
    public static int pickNumberInRange(final int startInclusive, final int endInclusive) {
        validateRange(startInclusive, endInclusive);
        return startInclusive + RANDOM.nextInt(endInclusive - startInclusive + 1);
    }

    private static void validateRange(final int startInclusive, final int endInclusive) {
        if (endInclusive == Integer.MAX_VALUE) {
            throw new IllegalArgumentException("끝값이 너무 큽니다. (스택 오버플로우 발생이 가능합니다)");
        }

        if (endInclusive - startInclusive >= Integer.MAX_VALUE) {
            throw new IllegalArgumentException("입력값이 너무 큽니다.");
        }

        if (startInclusive > endInclusive) {
            throw new IllegalArgumentException("startInclusive가 endInclusive보다 클 수 없습니다.");
        }
    }
}
