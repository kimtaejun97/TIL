package ch2.item6;

public class CreateObj {



    public static void main(String[] args) {
        String s1 = new String("test");
        String s2 = new String("test");

        System.out.println(s1 == s2);


        boolean b1 = Boolean.valueOf("true");
        boolean b2 = Boolean.valueOf("true");

        System.out.println(b1 == b2);
        System.out.println(b1 == Boolean.TRUE);
    }
}
