package tree;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个二叉树，返回其节点值的锯齿形层序遍历。（即先从左往右，再从右往左进行下一层遍历，以此类推，层与层之间交替进行）。
 *
 * 例如：
 * 给定二叉树 [3,9,20,null,null,15,7],
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * 返回锯齿形层序遍历如下：
 *
 * [
 *   [3],
 *   [20,9],
 *   [15,7]
 * ]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/binary-tree-zigzag-level-order-traversal
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Solution103 {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        if (root == null) return List.of();
        ArrayList<List<Integer>> res = new ArrayList<>();
        zigzagLevelOrder(root, res, 0);
        return res;
    }

    private void zigzagLevelOrder(TreeNode root, ArrayList<List<Integer>> collection, int depth) {
        if (root == null) return;

        if (depth < collection.size()) {
            ArrayList<Integer> list = new ArrayList(collection.get(depth));

            if (depth % 2 == 0) {
                list.add(root.val);
            } else {
                list.add(0, root.val);
            }

            collection.set(depth, list);
        } else {
            collection.add(List.of(root.val));
        }

        zigzagLevelOrder(root.left, collection, depth + 1);
        zigzagLevelOrder(root.right, collection, depth + 1);
    }
}
