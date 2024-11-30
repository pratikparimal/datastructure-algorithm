package datastructure.tree.binarytree;

import java.util.LinkedList;
import java.util.Queue;

public class BinaryTreeDS {

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

    static class BinaryTree {
        static int idx = -1;
        public static Node buildTree(int[] nodes) {
            idx++;
            if(nodes[idx] == -1)
                return null;

            Node newNode = new Node(nodes[idx]);
            newNode.left = buildTree(nodes);
            newNode.right = buildTree(nodes);

            return newNode;
        }
    }

    //Pre Order Traversal
    //Root -> Left -> Right
    public static void preOrderTraversal(Node root) {
        if (root == null) {
            return;
        }
        System.out.print(root.data + " ");
        preOrderTraversal(root.left);
        preOrderTraversal(root.right);
    }

    //In Order Traversal
    //Left -> Root -> Right
    public static void inOrderTraversal(Node root){
        if (root == null) {
            return;
        }
        preOrderTraversal(root.left);
        System.out.print(root.data + " ");
        preOrderTraversal(root.right);
    }

    //Post Order Traversal
    //Left -> Right -> Root
    public static void postOrderTraversal(Node root) {
        if (root == null) {
            return;
        }
        postOrderTraversal(root.left);
        postOrderTraversal(root.right);
        System.out.print(root.data + " ");
    }

    public static void levelOrderTraversal(Node root) {
        if (root == null) {
            return;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        queue.add(null); //adding null to track the level of tree
        while (!queue.isEmpty()) {
            Node currNode = queue.remove();
            if (currNode == null) {
                System.out.println();
                if (queue.isEmpty()) {
                    break;
                } else {
                    queue.add(null);
                }
            } else {
                System.out.print(currNode.data + " ");
                if (currNode.left != null) {
                    queue.add(currNode.left);
                }
                if (currNode.right != null) {
                    queue.add(currNode.right);
                }
            }
        }

    }

    public static void main(String[] args) {
        int[] nodes = {1, 2, 4, -1, -1, 5, -1, -1, 3, -1, 6, -1, -1};
        Node root = BinaryTree.buildTree(nodes);
        System.out.println(root.data);
        preOrderTraversal(root);
        System.out.println();
        inOrderTraversal(root);
        System.out.println();
        postOrderTraversal(root);
        System.out.println();
        levelOrderTraversal(root);
        System.out.println();
    }
}
