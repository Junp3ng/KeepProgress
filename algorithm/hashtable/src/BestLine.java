import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个二维平面及平面上的 N 个点列表Points，其中第i个点的坐标为Points[i]=[Xi,Yi]。请找出一条直线，其通过的点的数目最多。
 *
 * 设穿过最多点的直线所穿过的全部点编号从小到大排序的列表为S，你仅需返回[S[0],S[1]]作为答案，若有多条直线穿过了相同数量的点，则选择S[0]值较小的直线返回，S[0]相同则选择S[1]值较小的直线返回。
 * 链接：https://leetcode-cn.com/problems/best-line-lcci
 *
 * 分析：数学上，在二维平面上表示一条直线，用的函数形式为 y=kx+b
 * 因此，可以用一个长度为2的数组L来表示一条直线，L[0]表示函数中的k，L[1]表示函数中的b。
 * 判断一个点Point[i]=[Xi, Yi]是否在直线上，只需要判断Yi == L[0]*Xi + L[1] 是否为真即可。
 */
public class BestLine {
    public int[] bestLine(int[][] points) {

    }

    public static void main(String[] args) {
        ArrayList<int[][]> inputs = new ArrayList<>();
        ArrayList<Integer> answers = new ArrayList<>();
        inputs.add(buildInput(List.of(point(0,0), point(1, 1), point(1, 0), point(2, 0))));

        answers
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

    static class Point{
        int x;
        int y;
        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}

