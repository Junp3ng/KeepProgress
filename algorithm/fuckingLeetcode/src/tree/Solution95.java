package tree;

import java.util.ArrayList;
import java.util.List;

/**
 *给定一个整数 n，生成所有由 1 ... n 为节点所组成的 二叉搜索树 。
 */
public class Solution95 {
    public List<TreeNode> generateTrees(int n) {
        if (n == 0) return List.of();
        if (n == 1) return List.of(new TreeNode(1));
        return generateTrees(1, n);
    }

    private List<TreeNode> generateTrees(int start, int end) {
        if (start == end) return List.of(new TreeNode(start));
        if (start > end) return List.of();
        var res = new ArrayList<TreeNode>();

        for (int i = start; i <= end; i ++){
            var left = new ArrayList<TreeNode>(generateTrees(start, i - 1));
            var right = new ArrayList<TreeNode>(generateTrees(i + 1, end));
            if (left.isEmpty()) {
                for (TreeNode node : right) {
                    res.add(new TreeNode(i, null, node));
                }
            } else if (right.isEmpty()) {
                for (TreeNode node : left) {
                    res.add(new TreeNode(i, node, null));
                }
            } else {
                for (TreeNode leftNode: left) {
                    for (TreeNode rightNode: right) {
                        res.add(new TreeNode(i, leftNode, rightNode));
                    }
                }
            }
        }


        return res;
    }
}
