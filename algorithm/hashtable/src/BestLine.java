import javax.swing.text.html.parser.Parser;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 给定一个二维平面及平面上的 N 个点列表Points，其中第i个点的坐标为Points[i]=[Xi,Yi]。请找出一条直线，其通过的点的数目最多。
 * <p>
 * 设穿过最多点的直线所穿过的全部点编号从小到大排序的列表为S，你仅需返回[S[0],S[1]]作为答案，若有多条直线穿过了相同数量的点，则选择S[0]值较小的直线返回，S[0]相同则选择S[1]值较小的直线返回。
 * 链接：https://leetcode-cn.com/problems/best-line-lcci
 * <p>
 * 分析：数学上，在二维平面上表示一条直线，用的函数形式为 y=kx+b
 * 因此，可以用一个长度为2的数组L来表示一条直线，L[0]表示函数中的k，L[1]表示函数中的b。
 * 判断一个点Point[i]=[Xi, Yi]是否在直线上，只需要判断Yi == L[0]*Xi + L[1] 是否为真即可。
 */
public class BestLine {


    public int[] bestLine(int[][] points) {
//        var pointToLine = new HashMap<Integer, ArrayList<Integer>>();
        var lines = new ArrayList<int[]>();

        for (int i = 0; i < points.length; i++) {
            var p1 = points[i];
            for (int j = i + 1; j < points.length; j++) {
                var p2 = points[j];
                var line = getABC(p1, p2);
//                System.out.println(Arrays.toString(line));
                var containsIndex = contains(lines, line);
                if (containsIndex == -1) {
                    lines.add(line);
                }
            }
        }
        var lineToCount = new HashMap<Integer, Integer>();
        var lineToPoint = new HashMap<Integer, ArrayList<Integer>>();

        for (int j = 0; j < points.length; j++) {
            var point = points[j];
            for (int i = 0; i < lines.size(); i ++) {
                var line = lines.get(i);
                if (line[0] * point[0] + line[1] * point[1] + line[2] == 0) {
                    var pList = lineToPoint.getOrDefault(i, new ArrayList<>());
                    pList.add(j);
                    lineToPoint.put(i,pList);
                    lineToCount.put(i, lineToCount.getOrDefault(i, 0) + 1);
                }
            }
        }


        AtomicInteger maxCount = new AtomicInteger(-1);
        lineToCount.forEach((key, value) -> {
            if (value > maxCount.get()) maxCount.set(value);
        });

        var keyList = new ArrayList<Integer>();
        lineToCount.forEach((key, value) -> {
            if (value == maxCount.get()) {
                keyList.add(key);
            }
        });
        AtomicInteger s0 = new AtomicInteger(Integer.MAX_VALUE);
        AtomicInteger s1 = new AtomicInteger(Integer.MAX_VALUE);
        keyList.forEach(key -> {
            var integers = lineToPoint.get(key);
            integers.sort(Integer::compareTo);
            if (integers.get(0) < s0.get()) {
                s0.set(integers.get(0));
                s1.set(integers.get(1));
            } else if (integers.get(0) == s0.get() && integers.get(1) < s1.get()) {
                s1.set(integers.get(1));
            }
        });

        return new int[]{s0.get(), s1.get()};
    }

    public int contains(ArrayList<int[]> lines, int[] line) {
        if (line[0] == 0 || line[1] == 0) {
            for (int i = 0; i < lines.size(); i++) {
                var _line = lines.get(i);
//                if (_line[0] * _line[1] != 0) continue;
                if ((line[0] == 0) ^ (_line[0] == 0)) continue;
                if ((line[1] == 0) ^ (_line[1] == 0)) continue;
                if (line[2] == 0 && _line[2] == 0) return i;
                if (line[0] != 0) {
                    if (line[0] % _line[0] != 0) continue;
                } else {
                    if (line[1] % _line[1] != 0) continue;
                }
                if (line[2] % _line[2] != 0) continue;
                return i;
            }
            return -1;
        }

        for (int i = 0; i < lines.size(); i++) {
            var _line = lines.get(i);
            if (_line[0] * line[1] != _line[1] * line[0]) continue;
            if (_line[2] * line[0] != _line[0] * line[2]) continue;
            return i;
        }

        return -1;
    }

    public int[] getABC(int[] p1, int[] p2) {
        int p1x = p1[0];
        int p1y = p1[1];
        int p2x = p2[0];
        int p2y = p2[1];
        int vx = p2x - p1x;
        int vy = p2y - p1y;
        return new int[]{vy, -vx, vx * (vx * p1y - vy * p1x)};
    }

    public static void main(String[] args) {
        var ps = Arrays.asList(
                point(-38935,27124),
                point(-39837,19604),
                point(-7086,42194),
                point(-11571,-23257),
                point(115,-23257),
                point(20229,5976),
                point(24653,-18488),
                point(11017,21043),
                point(-9353,16550),
                point(-47076,15237),
                point(-36686,42194),
                point(-17704,1104),
                point(31067,7368),
                point(-20882,42194),
                point(-19107,-10597),
                point(-14898,24506),
                point(-20801,42194),
                point(-52268,40727),
                point(-14042,42194),
                point(-23254,42194),
                point(-30837,-53882),
                point(1402,801),
                point(-6411,42194),
                point(-12210,22901),
                point(-8213,-19441),
                point(-26939,20810),
                point(30656,-23257),
                point(-27195,21649),
                point(-33780,2717),
                point(23617,27018),
                point(12266,3608)
        );
        System.out.println(Arrays.toString(new BestLine().bestLine(buildInput(ps))));
    }


    static int[][] buildInput(List<Point> points) {
        int[][] result = new int[points.size()][2];
        for (int i = 0; i < points.size(); i++) {
            result[i][0] = points.get(i).x;
            result[i][1] = points.get(i).y;
        }
        return result;
    }

    static Point point(int x, int y) {
        return new Point(x, y);
    }

    static class Point {
        int x;
        int y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}

