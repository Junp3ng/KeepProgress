package tree;

import java.util.*;


/**
 * 给定一个 N 叉树，返回其节点值的 前序遍历 。
 * <p>
 * N 叉树 在输入中按层序遍历进行序列化表示，每组子节点由空值 null 分隔（请参见示例）。
 * <p>
 *  
 * <p>
 * 进阶：
 * <p>
 * 递归法很简单，你可以使用迭代法完成此题吗?
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/n-ary-tree-preorder-traversal
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Solution589 {
    public List<Integer> preorder(Node root) {

        if (root == null) return new ArrayList<Integer>();
        ArrayList<Integer> res = new ArrayList<Integer>();
        res.add(root.val);

        if (root.children != null) {
            for (int i = 0; i < root.children.size(); i++) {
                res.addAll(preorder(root.children.get(i)));
            }
        }
        return res;
    }

    /**
     * 迭代法
     * @param root
     * @return
     */
    public List<Integer> preorder2(Node root) {
        if (root == null) return List.of();
        if (root.children == null) return List.of(root.val);
        LinkedList<Node> stack = new LinkedList<>();
        stack.push(root);
        LinkedList<Integer> res = new LinkedList<>();
        while (!stack.isEmpty()) {
            var node = stack.pollLast();
            res.add(node.val);
            if (node.children != null) {
                for (int i = node.children.size() - 1; i >= 0; i--) {
                    stack.add(node.children.get(i));
                }
            }
        }
        return res;
    }


}

class Node {
    public int val;
    public List<Node> children;

    public Node() {
    }

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, List<Node> _children) {
        val = _val;
        children = _children;
    }
};