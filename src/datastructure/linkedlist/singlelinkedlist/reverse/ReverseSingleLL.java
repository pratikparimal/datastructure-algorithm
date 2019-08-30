package datastructure.linkedlist.singlelinkedlist.reverse;

public class ReverseSingleLL {

    public static class Node {
        int data;
        Node next;

        public Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    public static Node head = null;

    public static Node reverseLL(Node node) throws Exception {
        if (node == null)
            throw new Exception("Empty Linked List");
        Node prev, curr, nxt;
        prev = nxt = null;
        curr = node;
        while (curr != null) {
            nxt = curr.next;
            curr.next = prev;
            prev = curr;
            curr = nxt;
        }
        node = prev;
        return node;
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
        System.out.print("Input LL : ");
        printList(head);
        Node reverse = reverseLL(head);
        System.out.print("Reversed LL : ");
        printList(reverse);
    }
}
