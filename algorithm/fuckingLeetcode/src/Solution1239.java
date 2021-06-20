import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个字符串数组 arr，字符串 s 是将 arr 某一子序列字符串连接所得的字符串，如果 s 中的每一个字符都只出现过一次，那么它就是一个可行解。
 *
 * 请返回所有可行解 s 中最长长度。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/maximum-length-of-a-concatenated-string-with-unique-characters
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Solution1239 {
    public int maxLength(List<String> arr) {
        List<Integer> masks = new ArrayList<>();
        for (String str : arr) {
            int mask = 0;

            for (char c : str.toCharArray()) {
                int ch = c - 'a';
                if (((mask >> ch) & 1) != 0) {
                    // 此时说明mask中已存在此字符
                    mask = 0;
                    break;
                }
                mask |= 1 << ch;
            }
            if (mask != 0) {
                masks.add(mask);
            }
        }

        return backtrace(masks, 0, 0);
    }

    private int backtrace(List<Integer> masks, int pos, int mask) {
        if (masks.size() == pos) {
            return Integer.bitCount(mask);
        }
        int count = 0;
        if ((masks.get(pos) & mask) == 0) {
            count = backtrace(masks, pos + 1, mask | masks.get(pos));
        }
        return Integer.max(count, backtrace(masks, pos + 1, mask));
    }

    public static void main(String[] args) {
        var i = Integer.parseInt("11111111111111111111111111", 2);
        System.out.println("i=" + i);
        int mask = 0;
        char c = 'd';
        var ch = c - 'a';
        mask |= 1 << ch;
        System.out.println("mask=" + mask);
        System.out.println("mask binary=" + Integer.toBinaryString(mask));
    }
}
