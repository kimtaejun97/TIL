package ch2.item09;

public class AppRunner2 {
    public static void main(String[] args) {
        try (MyResource myResource1 = new MyResource();
             MyResource myResource2 = new MyResource()) {
            myResource1.doSomething();
            myResource2.doSomething();
        }

    }
}
