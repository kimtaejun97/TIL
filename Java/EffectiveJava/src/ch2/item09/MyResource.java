package ch2.item09;

import java.io.Closeable;
import java.io.IOException;

public class MyResource implements Closeable {

    public void doSomething(){
        System.out.println("Do something");
        throw new FirstException();
    }

    @Override
    public void close() {
        throw new SecondException();
    }

}