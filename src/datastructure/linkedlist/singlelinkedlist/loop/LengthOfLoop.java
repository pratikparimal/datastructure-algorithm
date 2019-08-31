package datastructure.linkedlist.singlelinkedlist.loop;

public class LengthOfLoop {

    public static class Node {
        int data;
        Node next;

        public Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    public static Node head = null;

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
    private static int getLengthOfLoop(Node node) throws Exception {
        if (node == null)
            throw new Exception("Empty Linked List");
        Node slowPtr, fastPtr;
        slowPtr = fastPtr = node;
        while (slowPtr != null && fastPtr != null && fastPtr.next != null) {
            fastPtr = fastPtr.next.next;
            slowPtr = slowPtr.next;
            if (fastPtr == slowPtr) {
                return countNodeInLoop(slowPtr);
            }
        }
        return 0;
    }

    public static int countNodeInLoop(Node node) {
        int count = 0;
        Node temp = node;
        while (temp.next != node) {
            count++;
            temp = temp.next;
        }
        return count;
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
        int lengthOfLoop = getLengthOfLoop(head);
        System.out.println("Length of Loop : " + lengthOfLoop);
    }
}
