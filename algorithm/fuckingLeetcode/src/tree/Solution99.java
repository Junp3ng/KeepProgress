package tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

public class Solution99 {
    public void recoverTree(TreeNode root) {
        if (root == null) {
            return;
        }
        ArrayList<Integer> array = new ArrayList<Integer>();
        inorder(root, array);
        ArrayList<Integer> badIndexes = getBadIndex(array);
        if (badIndexes.size() == 1) {
            int index = badIndexes.get(0);
            exchangeNode(root, array.get(index), array.get(index + 1));
        } else if(badIndexes.size() == 2) {
            int index1 = badIndexes.get(0);
            int index2 = badIndexes.get(1);
            exchangeNode(root, array.get(index1), array.get(index2 + 1));
        }


    }

    private void inorder(TreeNode root, ArrayList<Integer> arrayList) {
        if (root == null) {
            return;
        }
        inorder(root.left, arrayList);
        arrayList.add(root.val);
        inorder(root.right, arrayList);
    }

    private ArrayList<Integer> getBadIndex(ArrayList<Integer> arrayList) {
        ArrayList<Integer> res = new ArrayList<Integer>();
        for (int i = 0; i < arrayList.size() - 1; i++) {
            if (arrayList.get(i) > arrayList.get(i + 1)) {
                res.add(i);
            }
        }
        return res;
    }

    private void exchangeNode(TreeNode root, int value1, int value2) {
        if (root == null) {
            return;
        }
        TreeNode node1 = null;
        TreeNode node2 = null;
        Stack<TreeNode> stack = new Stack<TreeNode>();
        while((node1 == null || node2 == null) && (!stack.isEmpty() || root != null)) {
            while(root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            if (root.val == value1) {
                node1 = root;
            } else if (root.val == value2) {
                node2 = root;
            }
            root = root.right;
        }
        if (node1 != null && node2 != null) {
            node2.val = value1;
            node1.val = value2;
        }
    }
}
