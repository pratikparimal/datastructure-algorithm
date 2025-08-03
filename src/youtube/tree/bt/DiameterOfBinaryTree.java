package youtube.tree.bt;

public class DiameterOfBinaryTree {

    /**
     * Diameter of Binary tree is the longest path that can be traversed in a tree.
     * It is not necessary for a root to be part of longest path.
     * For any node, longest path will be max height of left tree + max height of right tree
     * So, to find diameter of binary tree, we need to find
     * the max of max height of left tree + max height of right tree
     */

    static class Node {
        int data;
        Node left, right;
        public Node(int data) {
            this.data = data;
            this.left = this.right = null;
        }
    }

    public static int getDiameter_Optimized(Node root, int[] maxD) {
        if (root == null) return 0;
        int lh = getDiameter_Optimized(root.left, maxD);
        int rh = getDiameter_Optimized(root.right, maxD);
        maxD[0] = Math.max(maxD[0], lh + rh);
        return 1 + Math.max(lh, rh);
    }

    static int maxD = 0;
    public static int getDiameter_BruteForce(Node root) {
        if (root == null)
            return 0;
        int lh = height(root.left);

        int rh = height(root.right);
        maxD = Math.max(maxD, lh + rh);
        getDiameter_BruteForce(root.left);
        getDiameter_BruteForce(root.right);
        return maxD;
    }

    public static int height(Node node) {
        if (node == null) return 0;
        int lh = height(node.left);
        int rh = height(node.right);
        return 1 + Math.max(lh, rh);
    }

    public static void main(String[] args) {
        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.right.left = new Node(4);
        root.right.left.left = new Node(5);
        root.right.left.left.left = new Node(6);
        root.right.right = new Node(7);
        root.right.right.right = new Node(8);
        root.right.right.right.right = new Node(9);

        System.out.println(getDiameter_BruteForce(root));

        int[] diameter = new int[1];
        getDiameter_Optimized(root, diameter);
        System.out.println(diameter[0]);
    }
}

