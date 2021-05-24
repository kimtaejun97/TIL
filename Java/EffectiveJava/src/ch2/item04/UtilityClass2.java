package ch2.item04;

public class UtilityClass2 {
    public static String getName(){
        return "kim";
    }

    //인스턴스 생성 방지.
    private UtilityClass2(){
        throw new AssertionError();
    }

}
