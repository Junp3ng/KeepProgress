package tree;

import java.util.ArrayList;
import java.util.List;

/**
 * 给你二叉树的根节点 root ，返回它节点值的 前序 遍历
 */
public class Solution144 {

    public List<Integer> preorderTraversal(TreeNode root) {
        if (root == null) return List.of();
        var res = new ArrayList<Integer>();
        res.add(root.val);
        var tmp = preorderTraversal(root.left);
        if (!tmp.isEmpty()) {
            res.addAll(tmp);
        }
        tmp = preorderTraversal(root.right);
        if (!tmp.isEmpty()) {
            res.addAll(tmp);
        }
        return res;
    }

}
