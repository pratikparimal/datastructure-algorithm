package youtube.tree.bt;

import java.util.LinkedList;
import java.util.Queue;

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

    static int index = -1;

    public static BinaryTree.Node createBT(int[] nodes) {
        index++;
        if (nodes[index] == -1)
            return null;
        BinaryTree.Node newNode = new BinaryTree.Node(nodes[index]);
        newNode.left = createBT(nodes);
        newNode.right = createBT(nodes);
        return newNode;
    }

    public static void main(String[] args) {
        int[] nodes = {1, 2, 4, -1, -1, 5, -1, -1, 3, -1, 6, -1, -1};
        BinaryTree.Node root = createBT(nodes);
        System.out.println(String.format("Depth of a BTree: Recursive = %d, Iterative = %d",
                maxDepthRecursive(root), maxDepthIterative(root)));
    }
}
