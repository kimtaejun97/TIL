import java.util.ArrayList;
import java.util.List;

public class Item32 {

    public static void main(String[] args) {

        List<String> result = flatten(List.of(List.of("aaa"), List.of("bbb"), List.of("ccc")));

    }

    static <T> List<T> flatten(List<List<? extends T>> lists) {
        List<T> result = new ArrayList<>();

        for(List<? extends T> list : lists){
            result.addAll(list);
        }
        return result;
    }
}
