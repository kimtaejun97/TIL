package ch2.item04;

public abstract class UtilityClass {
    public static String getName(){
        return "kim";
    }

    public static void main(String[] args) {
        UtilityClass.getName();

//         추상 클래스 인스턴스 생성 불가능
//        UtilityClass utilityClass = new UtilityClass(); (x)

    }
}
