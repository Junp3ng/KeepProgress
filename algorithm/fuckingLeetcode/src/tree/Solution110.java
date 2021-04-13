package tree;


import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 给定一个二叉树，判断它是否是高度平衡的二叉树。
 * <p>
 * 本题中，一棵高度平衡二叉树定义为：
 * 一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1
 * <a href="https://leetcode-cn.com/problems/balanced-binary-tree/">点击这里查看网址</a>
 */
public class Solution110 {


    public boolean isBalanced(TreeNode root) {
        if (root == null) return true;
        var leftHeight = 0;
        var rightHeight = 0;
        if (root.left != null) {
            leftHeight += getHeight(root.left);
        }
        if (root.right != null) {
            rightHeight += getHeight(root.right);
        }

        return Math.abs(leftHeight - rightHeight) <= 1 && isBalanced(root.left) && isBalanced(root.right);
    }

    private int getHeight(TreeNode tree) {
        if (tree == null) return 0;
        return 1 +  Math.max(getHeight(tree.left), getHeight(tree.right));
    }
    private static Solution110 solution = new Solution110();
    public static void main(String[] args) {

        var input = Arrays.asList(3, 9, 20, null, null, 15, 17);
        TreeNode root = getRoot(input, 0);
        assert root != null;
        testSolution(Arrays.asList(3, 9, 20, null, null, 15, 17), true);
        testSolution(Arrays.asList(1, 2, 2, 3, 3, null, null, 4, 4), false);
        testSolution(Arrays.asList(1,2,2,3,null,null,3,4,null,null,4), false);
    }
    private static void testSolution(List<Integer> input, boolean answer) {
        var root = getRoot(input, 0);
        System.out.println("---------input----------");
        System.out.println(Arrays.toString(input.toArray()));
        System.out.println("answer: " + answer);
        System.out.println("---------result----------");
        assert root != null;
        var balanced = solution.isBalanced(root);
        System.out.println(balanced);
        System.out.println("correction: " + (answer == balanced));
    }

    private static TreeNode getRoot(List<Integer> input, int currentIndex) {
        if (input.size() <= currentIndex || input.get(currentIndex) == null) return null;

        TreeNode root = new TreeNode(input.get(currentIndex));
        if (currentIndex + 1 < input.size() && input.get(currentIndex + 1) != null) {

        }
        return root;
    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }

    void print() {
        System.out.println(val);
        if (left != null) {
            left.print();
        }
        if (right != null) {
            right.print();
        }
    }
}