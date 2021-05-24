package ch2.item03;

import java.util.function.Supplier;

public class Client {
    public static void main(String[] args) {
        //생성 불가능
//        Singleton1 singleton1 = new Singleton1();

        Singleton1 singleton1 = Singleton1.INSTANCE;
        Singleton2 singleton2 = Singleton2.getInstance();
        Supplier<Singleton2> singleton2Supplier= Singleton2::getInstance;

        Singleton3 singleton3 =Singleton3.INSTANCE;
    }
}
