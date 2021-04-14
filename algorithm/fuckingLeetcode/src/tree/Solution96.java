package tree;

import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;

/**
 * 给定一个整数 n，求以 1 ... n 为节点组成的二叉搜索树有多少种？
 */
public class Solution96 {

    private HashMap<Integer, Integer> hashMap = new HashMap<>();

    /**
     * 核心思想是，end-start相同时，搜索树的数量是一样的，使用map存起来就可以很快。
     */
    public int numTrees(int n) {
        if (n <= 0) return 0;
        if (n == 1) return 1;
        hashMap.put(1, 2);
        hashMap.put(2, 5);

        return numTress(1, n);
    }

    private int numTress(int start, int end) {
        if (start >= end) return 1;
        if (hashMap.containsKey(end - start)) return hashMap.get(end - start);
        var count = 0;
        for (int i = start; i <= end; i++) {
            count += numTress(i + 1, end) * numTress(start,i - 1);
        }
        hashMap.put(end - start, count);
        return count;
    }

    public static void main(String[] args) {
        var solution = new Solution96();
        var start = Calendar.getInstance().getTimeInMillis();
        System.out.println(solution.numTrees(100));
        var end = Calendar.getInstance().getTimeInMillis();
        System.out.println(end - start);
    }
}
