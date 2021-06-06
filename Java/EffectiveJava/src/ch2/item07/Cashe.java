package ch2.item07;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.util.WeakHashMap;

public class Cashe {
    public static void main(String[] args) {
        String key = "key1";
        Object value = new Object();

        Map<String, Object> cache = new WeakHashMap<>();
        cache.put(key,value);
    }

}
