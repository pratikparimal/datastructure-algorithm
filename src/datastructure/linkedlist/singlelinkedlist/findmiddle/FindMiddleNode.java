package datastructure.linkedlist.singlelinkedlist.findmiddle;

public class FindMiddleNode {

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
     * Increment slowPrt by 1, and fastPtr by 2
     * Once fastPtr reaches last, print slowPtr.
     * slowPtr will always be at middle till fast ptr reaches end
     * @param node
     * @return slowPrt
     * @throws Exception
     */
    public static Node findMiddle(Node node) throws Exception {
        if (node == null)
            throw new Exception("Empty Linked List");
        Node slowPtr, fastPtr;
        slowPtr = fastPtr = node;
        while (fastPtr != null && fastPtr.next != null) {
            fastPtr = fastPtr.next.next;
            slowPtr = slowPtr.next;
        }
        return slowPtr;
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
        insertNode(8);
        System.out.print("Input LL : ");
        printList(head);
        Node middle = findMiddle(head);
        System.out.print("Middle of LL : " + middle.data);
    }
}
