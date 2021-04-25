package tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 给你一个二叉树，请你返回其按 层序遍历 得到的节点值。 （即逐层地，从左到右访问所有节点）。
 *
 *  
 *
 * 示例：
 * 二叉树：[3,9,20,null,null,15,7],
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * 返回其层序遍历结果：
 *
 * [
 *   [3],
 *   [9,20],
 *   [15,7]
 * ]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/binary-tree-level-order-traversal
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Solution102 {
    public List<List<Integer>> levelOrder(TreeNode root) {
        if (root == null) {
            return List.of();
        }
        ArrayList<List<Integer>> list = new ArrayList<>();

        levelOrder(root, list, 0);
        return list;
    }

    private void levelOrder(TreeNode root, ArrayList<List<Integer>> arrayList, int depth) {
        if (root == null) return;
        if (arrayList.size() > depth) {
            var list = new ArrayList<Integer>(arrayList.get(depth));
            list.add(root.val);
            arrayList.set(depth, list);
        } else {
            arrayList.add(new ArrayList(List.of(root.val)));
        }
        levelOrder(root.left, arrayList, depth + 1);
        levelOrder(root.right, arrayList, depth + 1);
    }

    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(0, 10);
        list.add(0, 30);
        System.out.println(Arrays.toString(list.toArray()));
    }
}

