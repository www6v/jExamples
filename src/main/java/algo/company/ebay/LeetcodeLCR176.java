package algo.company.ebay;

/// 是否是平衡二叉树
public class Solution {

    static class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;
        public TreeNode(int val){
            this.val = val;
        }
    }

        public static void main(String args[]) {
            TreeNode treeNode = new TreeNode(5);
            TreeNode treeNode1 = new TreeNode(1);
            TreeNode treeNode2 = new TreeNode(3);
            treeNode.left = treeNode1;
            treeNode.right = treeNode2;

            TreeNode treeNode4 = new TreeNode(6);
            treeNode2.left = treeNode4;

            System.out.println(is_bst(treeNode));
        }
        static boolean is_bst(TreeNode root) {
            return isValidBST(root, Long.MIN_VALUE, Long.MAX_VALUE);
        }

        public static boolean isValidBST(TreeNode node, long lower, long upper) {
            if(node == null) {
                return true;
            }

            if(node.val <= lower || node.val >= upper) {
                return false;
            }
            return isValidBST(node.left, lower, node.val) &&
                    isValidBST(node.right, node.val, upper) ;
        }

    }

