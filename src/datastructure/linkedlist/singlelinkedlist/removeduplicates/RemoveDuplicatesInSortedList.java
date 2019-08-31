package datastructure.linkedlist.singlelinkedlist.removeduplicates;

public class RemoveDuplicatesInSortedList {
    public static class Node {
        int data;
        Node next;

        public Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    public static Node head = null;

    public static Node removeDuplicates(Node node) throws Exception {
        if (node == null)
            throw new Exception("Empty Linked List");
        Node curr = node;
        while (curr != null) {
            while (curr.next != null && curr.next.data == curr.data) {
                curr.next = curr.next.next;
            }
            curr = curr.next;
        }
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
        insertNode(5);
        insertNode(5);
        insertNode(5);
        insertNode(3);
        insertNode(2);
        insertNode(2);
        insertNode(1);
        insertNode(1);
        insertNode(1);
        System.out.print("Input LL : ");
        printList(head);
        Node duplicateRemoved = removeDuplicates(head);
        System.out.print("Duplicate removed from LL : ");
        printList(duplicateRemoved);
    }
}
