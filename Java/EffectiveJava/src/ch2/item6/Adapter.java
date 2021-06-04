package ch2.item6;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Adapter {
    public static void main(String[] args) {
        Map<String, Integer> students = new HashMap<>();

        students.put("kim",23);
        students.put("park",25);

        Set<String> names = students.keySet();
        Set<String> names2 = students.keySet();

        names.remove("kim");

        System.out.println(names.size());
        System.out.println(names2.size());
    }
}
