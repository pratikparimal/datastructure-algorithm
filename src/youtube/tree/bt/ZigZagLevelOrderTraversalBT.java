package youtube.tree.bt;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

public class ZigZagLevelOrderTraversalBT {

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

    private static ArrayList<ArrayList<Integer>> zigzagLevelOrder(Node root) {
        boolean leftToRight = true;
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        if (root == null) return result;
        Queue<Node> q = new LinkedList<>();
        q.offer(root);
        while (!q.isEmpty()) {
            int size = q.size();
            ArrayList<Integer> nodesAtSameLevel = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                if (q.peek().left != null) q.offer(q.peek().left);
                if (q.peek().right != null) q.offer(q.peek().right);
                nodesAtSameLevel.add(q.poll().data);
            }
            if (leftToRight) {
                result.add(nodesAtSameLevel);
            } else {
                Collections.reverse(nodesAtSameLevel);
                result.add(nodesAtSameLevel);
            }
            leftToRight = !leftToRight;
        }
        return result;
    }

    public static void main(String[] args) {
        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.right.left = new Node(5);
        root.right.right = new Node(6);

        ArrayList<ArrayList<Integer>> result = zigzagLevelOrder(root);
        System.out.println(result);
    }
}
