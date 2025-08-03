package youtube.tree.bt;

import java.util.*;

public class BinaryTree {

    public static class Node {
        int data;
        Node left;
        Node right;

        public Node(int data) {
            this.data = data;
            this.left = null;
            this.right = null;
        }
    }

    static Node root = null;

    static int index = -1;

    public static Node createBT(int[] nodes) {
        index++;
        if (nodes[index] == -1)
            return null;
        Node newNode = new Node(nodes[index]);
        newNode.left = createBT(nodes);
        newNode.right = createBT(nodes);
        return newNode;
    }

    //preorder -> root left right
    public static void preOrderTraversal(Node node) {
        if (node == null)
            return;
        System.out.print(node.data + " ");
        preOrderTraversal(node.left);
        preOrderTraversal(node.right);
    }

    //inorder -> left root right
    public static void inOrderTraversal(Node node) {
        if (node == null)
            return;
        inOrderTraversal(node.left);
        System.out.print(node.data + " ");
        inOrderTraversal(node.right);
    }

    //postorder -> left right root
    public static void postOrderTraversal(Node node) {
        if (node == null)
            return;
        postOrderTraversal(node.left);
        postOrderTraversal(node.right);
        System.out.print(node.data + " ");
    }

    public static void levelOrderTraversal(Node node) {
        Queue<Node> queue = new LinkedList<>();
        ArrayList<ArrayList<Integer>> list = new ArrayList<>();
        if (node == null)
            return;
        queue.offer(node);
        while (!queue.isEmpty()) {
            int size = queue.size();
            ArrayList<Integer> tempList = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                if (queue.peek().left != null) queue.offer(queue.peek().left);
                if (queue.peek().right != null) queue.offer(queue.peek().right);
                tempList.add(queue.poll().data);
            }
            list.add(tempList);
        }
        for (ArrayList<Integer> al: list) {
            al.forEach(x -> System.out.print(x + " "));
        }
    }

    public static void levelOrderTraversal2(Node node) {
        Queue<Node> queue = new LinkedList<>();
        if (node == null)
            return;
        queue.offer(node);
        while (!queue.isEmpty()) {
            if (queue.peek().left != null) queue.offer(queue.peek().left);
            if (queue.peek().right != null) queue.offer(queue.peek().right);
            Node currNode = queue.poll();
            System.out.print(currNode.data + " ");
        }
    }

    public static void iterativePreOrderTraversal(Node node) {
        if (node == null)
            return;
        Stack<Node> stack = new Stack<>();
        stack.push(node);
        while (!stack.isEmpty()) {
            Node currNode = stack.pop();
            System.out.print(currNode.data + " ");
            if (currNode.right != null) stack.push(currNode.right);
            if (currNode.left != null) stack.push(currNode.left);
        }
    }

    public static void iterativeInOrderTraversal(Node node) {
        Stack<Node> stack = new Stack<>();
        while (true) {
            if (node != null) {
                stack.push(node);
                node = node.left;
            } else {
                if (stack.isEmpty())
                    break;
                node = stack.pop();
                System.out.print(node.data + " ");
                node = node.right;
            }
        }
    }

    public static void iterativePostOrderTraversal(Node node) {
        Stack<Node> s1 = new Stack<>();
        Stack<Node> s2 = new Stack<>();
        s1.push(node);
        while (!s1.isEmpty()) {
            node = s1.pop();
            s2.push(node);
            if (node.left != null) s1.push(node.left);
            if (node.right != null) s1.push(node.right);
        }
        while (!s2.isEmpty()) {
            node = s2.pop();
            System.out.print(node.data + " ");
        }
    }

    public static void iterativePostOrderTraversal2(Node node) {
        Stack<Node> s1 = new Stack<>();
        ArrayList<Integer> al = new ArrayList<>();
        s1.push(node);
        while (!s1.isEmpty()) {
            node = s1.pop();
            al.add(node.data);
            if (node.left != null) s1.push(node.left);
            if (node.right != null) s1.push(node.right);
        }
        Collections.reverse(al);
        System.out.print(al);
    }

    /**
     * Rule:
     * pop
     * if num == 1
     *  add to preorder
     *  num++
     *  go left
     *
     * if num == 2
     *  add to inorder
     *  num++
     *  go right
     *
     * if num == 3
     *  add to postorder
     *  no more num increment of num
     *
     */
    public static void all3TraversalsInASingleGo(Node node) {

        class Pair {
            Node n;
            int num;
            public Pair(Node n, int num) {
                this.n = n;
                this.num = num;
            }
        }
        List<Integer> preOrder = new ArrayList<>();
        List<Integer> inOrder = new ArrayList<>();
        List<Integer> postOrder = new ArrayList<>();

        Stack<Pair> stack = new Stack<>();
        stack.push(new Pair(node, 1));
        if (node == null)
            return;
        while (!stack.isEmpty()) {
            Pair p = stack.pop();
            if (p.num == 1) {
                preOrder.add(p.n.data);
                p.num++;
                stack.push(p);

                if (p.n.left != null)
                    stack.push(new Pair(p.n.left, 1));
            }
            else if (p.num == 2) {
                inOrder.add(p.n.data);
                p.num++;
                stack.push(p);

                if (p.n.right != null)
                    stack.push(new Pair(p.n.right, 1));
            }
            else {
                postOrder.add(p.n.data);
            }
        }
        System.out.println("Pre Order: " + preOrder);
        System.out.println("In Order: " + inOrder);
        System.out.println("Post Order: " + postOrder);
    }

    public static void main(String[] args) {
        int[] nodes = {1, 2, 4, -1, -1, 5, -1, -1, 3, -1, 6, -1, -1};
        root = createBT(nodes);
        System.out.println("Recursive Pre Order:");
        preOrderTraversal(root);
        System.out.println("\nIterative Pre Order:");
        iterativePreOrderTraversal(root);
        System.out.println("\nIn Order:");
        inOrderTraversal(root);
        System.out.println("\nIterative In Order:");
        iterativeInOrderTraversal(root);
        System.out.println("\nPost Order:");
        postOrderTraversal(root);
        System.out.println("\nIterative Post Order - 2 stack:");
        iterativePostOrderTraversal(root);
        System.out.println("\nIterative Post Order - 1 stack:");
        iterativePostOrderTraversal2(root);
        System.out.println("\nLevel Order:");
        levelOrderTraversal(root);
        System.out.println("\nLevel Order 2:");
        levelOrderTraversal2(root);
        System.out.println("\nAll Traversal in one go:");
        all3TraversalsInASingleGo(root);
    }
}

/**
 * Response:
 *
 * Recursive Pre Order:
 * 1 2 4 5 3 6
 * Iterative Pre Order:
 * 1 2 4 5 3 6
 * In Order:
 * 4 2 5 1 3 6
 * Iterative In Order:
 * 4 2 5 1 3 6
 * Post Order:
 * 4 5 2 6 3 1
 * Iterative Post Order - 2 stack:
 * 4 5 2 6 3 1
 * Iterative Post Order - 1 stack:
 * [4, 5, 2, 6, 3, 1]
 * Level Order:
 * 1 2 3 4 5 6
 * Level Order 2:
 * 1 2 3 4 5 6
 * All Traversal in one go:
 * Pre Order: [1, 2, 4, 5, 3, 6]
 * In Order: [4, 2, 5, 1, 3, 6]
 * Post Order: [4, 5, 2, 6, 3, 1]
 */