package ch2.item03;

public class Singleton1 {
    public static final Singleton1 INSTANCE = new Singleton1();
    int count =0;

    private Singleton1() {
        count++;
        if(count != 1){
            throw new IllegalStateException("this Object should be Singleton");
        }
    }

}
