
public class Solution65 {
    public boolean isNumber(String s) {
        if (s==null || s.isEmpty()) {
            return false;
        }
        char[] chars = s.toCharArray();
        int index = 0;
        int length = chars.length;
        if (chars[index] == '+' || chars[index] == '-') {
            index++;
            if (index == length) {
                // 不能只有一个+
                return false;
            }
            // 符号位后面必须是数字或小数点
            if (chars[index] != '.' && !isDigital(chars[index])) {
                return false;
            }
        }
        while(index < length) {
            char c = chars[index];
            if (isDigital(c)) {
                index++;
                continue;
            }
            if (c == '.') {
                return findPoint(chars, index);
            }
            if (isE(c)) {
                return findE(chars, index);
            }
            return false;
        }
        return true;

    }

    private boolean findPoint(char[] chars, int index) {
        boolean isFirst = index == 0;
        boolean isEnd = index == chars.length - 1;
        if (isFirst && isEnd) {
            // 不能只有一个点
            return false;
        } else if (isEnd) {
            // 小数点的两侧必须至少有一个数字
            return isDigital(chars[index - 1]);
        } else if (isFirst && !isDigital(chars[index + 1])) {
            // 小数点的两侧必须至少有一个数字
            return false;
        }
        index ++;
        while (index < chars.length) {
            char c = chars[index];
            if (isDigital(c)) {
                index++;
                continue;
            }
            if (isE(c)) {
                return findE(chars, index);
            }
            return false;
        }
        return true;
    }
    private boolean isE(char c) {
        return c == 'e' || c == 'E';
    }
    private boolean findE(char[] chars, int index) {
        boolean isStart = index == 0;
        boolean isEnd = index == chars.length - 1;
        if (isStart || isEnd) {
            // E 不能出现在开头或结尾
            return false;
        }
        if (chars[index - 1] == '+' || chars[index - 1] == '-' ) {
            // E 的前面不能是+号
            return false;
        }


        index++;
        if (chars[index] == '+' || chars[index] == '-') {
            index ++;
            if (index == chars.length) {
                return false;
            }
        }
        while(index < chars.length) {
            if (!isDigital(chars[index++])) {
                return false;
            }
        }
        return true;
    }

    private boolean isDigital(char c) {
        return c >= '0' && c <= '9';
    }

    public static void main(String[] args) {
        System.out.println((new Solution65()).isNumber(".1e2"));
    }
}
