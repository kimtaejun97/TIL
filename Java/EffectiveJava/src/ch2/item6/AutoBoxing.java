package ch2.item6;

public class AutoBoxing {
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        Long sum =0l;
        for(long i =0l; i<Integer.MAX_VALUE; i++)
            sum+= i;

        System.out.println(System.currentTimeMillis() -start);
    }
}
