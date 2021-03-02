import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class GroupAnagrams {
    public List<List<String>> groupAnagrams(String[] strs) {
        ArrayList<List<String>> result = new ArrayList<>();
        HashMap<String, ArrayList<String>> map = new HashMap<>();

        for (String str : strs) {
            char[] ca = str.toCharArray();
            Arrays.sort(ca);
            String s = String.valueOf(ca);
            if (map.containsKey(s)) {
                ArrayList<String> value = map.get(s);
                value.add(str);
            } else {
                ArrayList<String> value = new ArrayList<>();
                value.add(str);
                map.put(s, value);
            }
        }
        map.forEach((s, strings) -> {
            result.add(strings);
        });
        return result;
    }

    public static void main(String[] args) {

    }
}
