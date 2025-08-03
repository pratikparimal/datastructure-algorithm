package youtube.tree.bt;

import java.util.LinkedList;
import java.util.Queue;

import static tree.bt.BinaryTree.createBT;

public class DepthOfBinaryTree {

    /**
     * Two ways to find it
     * 1. Recursive
     * 2. Iterative (Level Order Traversal)
     *
     * With Level Order, it uses queue, ends up with O(n) space complexity
     *
     * Logic:
     * 1 + max(left, right)
     */
    public static int maxDepthRecursive(BinaryTree.Node root) {
        if (root == null)
            return 0;
        int left = maxDepthRecursive(root.left);
        int right = maxDepthRecursive(root.right);
        return (1 + Math.max(left, right));
    }

    /**
     * Iterative approach using level order traversal
     * Add null after every level to keep track of height
     */
    public static int maxDepthIterative(BinaryTree.Node root) {
        Queue<BinaryTree.Node> queue = new LinkedList<>();
        if (root == null) return 0;
        int height = 0;
        queue.offer(root);
        queue.offer(null);
        while (!queue.isEmpty()) {
            BinaryTree.Node node = queue.poll();
            if (node == null) {
                height++;
                if (queue.isEmpty()) break;
                else queue.offer(null);
            } else {
                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
            }
        }
        return height;
    }

    public static void main(String[] args) {
        int[] nodes = {1, 2, 4, -1, -1, 5, -1, -1, 3, -1, 6, -1, -1};
        BinaryTree.Node root = createBT(nodes);
        System.out.println(String.format("Depth of a BTree: Recursive = %d, Iterative = %d",
                maxDepthRecursive(root), maxDepthIterative(root)));
    }
}
