package ch3.item10;

import java.util.HashMap;
import java.util.Map;

public class equalsEx {
    public static void main(String[] args) {
        Map<String, Integer> map = new HashMap<>();
        Map<String, Integer> map2 = new HashMap<>();

        map.put("kim",25);
        map2.put("kim",25);

        System.out.println(map.equals(map2)); //true

    }
}
