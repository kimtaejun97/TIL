package ch2.item09;

import java.io.IOException;

public class AppRunner {
    public static void main(String[] args) throws IOException {
        MyResource myResource1 = new MyResource();
        MyResource myResource2 = null;
        try{
            myResource1.doSomething();
            try {
                myResource2 = new MyResource();
                myResource2.doSomething();
            }finally {
                myResource2.close();
            }
        }finally {
            myResource1.close();
        }
    }
}
