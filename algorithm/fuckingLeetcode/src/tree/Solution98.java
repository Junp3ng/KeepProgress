package tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;


/*
给定一个二叉树，判断其是否是一个有效的二叉搜索树。

假设一个二叉搜索树具有如下特征：

节点的左子树只包含小于当前节点的数。
节点的右子树只包含大于当前节点的数。
所有左子树和右子树自身必须也是二叉搜索树。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/validate-binary-search-tree
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Solution98 {
    /*
    核心思想是：
    1。 先把树展开成数组
    2。 检查此数组是否有序
     */
    public boolean isValidBST(TreeNode root) {
        if (root == null) return false;
        var arrayList = new LinkedList<Integer>();
        return isValidBST(root, arrayList);
    }

    private boolean isValidBST(TreeNode root, LinkedList<Integer> collection) {
        if (root == null) {
            return true;
        }
        boolean leftRes = isValidBST(root.left, collection);
        if (!collection.isEmpty() && root.val <= collection.getLast()) return false;
        collection.add(root.val);
        
        return leftRes && isValidBST(root.right, collection);
    }

}

class Solution98_1{
    /*
    核心思想是，使用区间来剪枝，在这个基础上，使用Long类型来避免数据越界
     */
    public boolean isValidBST(TreeNode root) {
        if (root == null) return false;
        return isValidBST(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }
    private boolean isValidBST(TreeNode root, long start, long stop) {
        if (root == null) return true;
        if (root.val <= start || root.val >= stop) return false;
        return isValidBST(root.left, start, root.val) && isValidBST(root.right, root.val, stop);
    }
}