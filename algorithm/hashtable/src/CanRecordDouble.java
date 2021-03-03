import java.util.*;
import java.util.function.ToIntBiFunction;
import java.util.function.ToIntFunction;

/**
 * 给定一个长度为偶数的整数数组 arr，只有对 arr 进行重组后可以满足 “对于每个 0 <= i < len(arr) / 2，都有 arr[2 * i + 1] = 2 * arr[2 * i]” 时，返回 true；否则，返回 false。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/array-of-doubled-pairs
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class CanRecordDouble {


    public boolean canReorderDoubled(int[] arr) {
        HashMap<Integer, Integer> countMap = new HashMap<>(); // 用来记录arr中有哪些元素，以及分别有几个
        for (int i : arr) {
            countMap.put(i, countMap.getOrDefault(i, 0) + 1);
        }

        Integer[] integers = new Integer[arr.length];
        for (int i = 0; i < integers.length; i++) {
            integers[i] = arr[i];
        }
        Arrays.sort(integers, Comparator.comparingInt(Math::abs));  // 将元素绝对值后排序，但不影响元素本身

        for (Integer x : integers) {
            if (countMap.getOrDefault(x, 0) <= 0) continue;  // 为什么要加这个判断呢？
            if (countMap.getOrDefault(2 * x, 0) <= 0) return false;
            countMap.put(x, countMap.getOrDefault(x, 0) - 1);
            countMap.put(2 * x, countMap.getOrDefault(2 * x, 0) - 1);
        }

        return true;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{-3, -1, -4, -2, 2, 4};
        CanRecordDouble obj = new CanRecordDouble();
        System.out.println(String.format("result=%b answer=%b", obj.canReorderDoubled(arr), false));
    }
}
