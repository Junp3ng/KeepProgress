package tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.stream.Collectors;


/**
 * 给定一棵二叉树，设计一个算法，创建含有某一深度上所有节点的链表（比如，若一棵树的深度为 D，则会创建出 D 个链表）。返回一个包含所有深度的链表的数组。
 *
 * 输入：[1,2,3,4,5,null,7,8]
 *
 *         1
 *        /  \
 *       2    3
 *      / \    \
 *     4   5    7
 *    /
 *   8
 *
 * 输出：[[1],[2,3],[4,5,7],[8]]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/list-of-depth-lcci
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Solution0403 {

    public ListNode[] listOfDepth(TreeNode tree) {
        if (tree == null) return new ListNode[0];
        var nodes = new ArrayList<ListNode>(List.of(new ListNode(tree.val)));

        var leftNodes = Arrays.stream(listOfDepth(tree.left)).collect(Collectors.toList());
        var rightNodes = Arrays.stream(listOfDepth(tree.right)).collect(Collectors.toList());
        for (int i = 0; i < Math.max(leftNodes.size(), rightNodes.size()); i++) {
            if (i >= leftNodes.size()) {
                nodes.addAll(rightNodes.subList(i, rightNodes.size()));
                break;
            }
            if (i >= rightNodes.size()) {
                nodes.addAll(leftNodes.subList(i, leftNodes.size()));
                break;
            }
            ListNode lastNode = leftNodes.get(i);
            while (lastNode.next != null) {
                lastNode = lastNode.next;
            }
            lastNode.next = rightNodes.get(i);
            nodes.add(leftNodes.get(i));
        }
        return nodes.toArray(ListNode[]::new);
    }

    public static void main(String[] args) {
        var intarray = new int[]{1, 2, 3};
        var list = Arrays.stream(intarray).boxed().collect(Collectors.toList());

        System.out.println(list);
    }


    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
    private static class ListNode {
      int val;
      ListNode next;
      ListNode(int x) { val = x; }
    }
}

