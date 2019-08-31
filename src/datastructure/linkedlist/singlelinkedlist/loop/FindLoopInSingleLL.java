package datastructure.linkedlist.singlelinkedlist.loop;

import java.util.HashSet;

public class FindLoopInSingleLL {
    public static class Node {
        int data;
        Node next;

        public Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    public static Node head = null;

    public static Boolean findLoop_Approach1_UsingHashingConcept(Node node) throws Exception {
        if (node == null)
            throw new Exception("Empty Linked List");
        HashSet<Node> nodeSet = new HashSet<>();
        while (node != null) {
            if (nodeSet.contains(node)) {
                return true;
            }
            nodeSet.add(node);
            node = node.next;
        }
        return false;
    }

    /**
     * Floyd’s Cycle-Finding Algorithm:
     *
     * This is the fastest method.
     * Traverse linked list using two pointers.
     * Move one pointer(slow_p) by one and another pointer(fast_p) by two.
     * If these pointers meet at the same node then there is a loop.
     * If pointers do not meet then linked list doesn’t have a loop.
     * @param node
     * @return true/false
     */
    private static Boolean findLoop_Approach2_UsingFloydCycleFindingAlgorithm(Node node) throws Exception {
        if (node == null)
            throw new Exception("Empty Linked List");
        Node slowPtr, fastPtr;
        slowPtr = fastPtr = node;
        while (slowPtr != null && fastPtr != null && fastPtr.next != null) {
            fastPtr = fastPtr.next.next;
            slowPtr = slowPtr.next;
            if (fastPtr == slowPtr) {
                return true;
            }
        }
        return false;
    }

    public static void insertNode(int new_data) {
        Node new_node = new Node(new_data);
        new_node.next = head;
        head = new_node;
    }

    public static void printList(Node node) throws Exception {
        if (node == null)
            throw new Exception("Empty Linked List");
        Node traverseNode = node;
        while (traverseNode != null){
            System.out.print(traverseNode.data + "  ");
            traverseNode = traverseNode.next;
        }
        System.out.println();
    }

    public static void main(String[] args) throws Exception {
        insertNode(4);
        insertNode(7);
        insertNode(2);
        insertNode(5);
        insertNode(6);
        insertNode(1);
        System.out.print("Input LL : ");
        printList(head);
        /**
         * creating loop in linked list
         */
        head.next.next.next.next.next.next = head.next.next;
        Node traverse = head; int i = 0;
        System.out.print("Loop in LL : ");
        while (i<12) {
            System.out.print(traverse.data + " ");
            traverse = traverse.next;
            i++;
        }
        System.out.println();
        Boolean loopPresent = findLoop_Approach1_UsingHashingConcept(head);
        System.out.println("Approach 1 : Loop Present in LL? : " + loopPresent);
        loopPresent = findLoop_Approach2_UsingFloydCycleFindingAlgorithm(head);
        System.out.println("Approach 2 : Loop Present in LL? : " + loopPresent);
    }
}
