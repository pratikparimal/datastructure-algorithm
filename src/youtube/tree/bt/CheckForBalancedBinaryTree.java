package youtube.tree.bt;

public class CheckForBalancedBinaryTree {

    /*
      Balanced Binary tree
      - For every node, difference of height b/w left subtree and right subtree <= 1
      - i.e. for every node, Math.abs(height(left) - height(right)) <= 1
    */

    /*
       An optimized solution to this would be to in-place check for both the requirement
        - if abs diff of height of left and right is <= 1, and
        - as this is recursion to same method, also keep checking
          if left or right tree is unbalanced at any point of time
        While calculating height itself we keep on checking if it is balanced.
        Hence it takes O(n) time.

        This code is exact similar to finding height of BTree, except the 3  checks.
     */
    public int isBalanced_Optimized(Node root) {
        if (root == null) return 0;
        int lHeight = isBalanced_Optimized(root.left);
        //check if left tree returns -1, then it's not balanced
        if (lHeight == -1) return -1;
        int rHeight = isBalanced_Optimized(root.right);
        //check if right tree returns -1, then it's not balanced
        if (rHeight == -1) return -1;
        //check if abs diff of height of left and right is > 1, then it's not balanced
        if (Math.abs(lHeight - rHeight) > 1) return -1;
        return 1 + Math.max(lHeight, rHeight);
    }

    /*
    Brute Force Solution:
      For each node starting from root, get the height of left subtree and right subtree
      Check if abs difference of height is <=1 or not

      This approach takes O(n^2)
      O(n) for finding height by traversing through all the nodes
      O(n) for traversing though all the nodes to check if its subtree is balanced
      so, O(n) * O(n) -> O(n^2)
     */
    public boolean isBalanced_BruteForce(Node root) {
        if (root == null) return true;
        int lh = height(root.left);
        int rh = height(root.right);
        if (Math.abs(lh - rh) > 1) return false;

        boolean isLBalanced = isBalanced_BruteForce(root.left);
        boolean isRBalanced = isBalanced_BruteForce(root.right);
        return isLBalanced && isRBalanced;
    }

    public int height(Node node) {
        if (node == null)
            return 0;
        int lh = height(node.left);
        int rh = height(node.right);
        return 1 + Math.max(lh, rh);
    }

    static class Node {
        int data;
        Node left;
        Node right;

        public Node(int data) {
            this.data = data;
            this.left = null;
            this.right = null;
        }
    }

    public static void main(String[] args) {

        // Creating a sample binary tree
        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        root.left.right.right = new Node(6);
        root.left.right.right.right = new Node(7);
        CheckForBalancedBinaryTree t = new CheckForBalancedBinaryTree();
        System.out.println(t.isBalanced_BruteForce(root));

        int res = t.isBalanced_Optimized(root);
        System.out.println(res != -1);

        //for another tree
        root.left.right.right = null;
        System.out.println(t.isBalanced_BruteForce(root));

        res = t.isBalanced_Optimized(root);
        System.out.println(res != -1);
    }
}