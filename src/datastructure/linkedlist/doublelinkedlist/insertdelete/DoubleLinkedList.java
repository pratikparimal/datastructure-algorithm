package datastructure.linkedlist.doublelinkedlist.insertdelete;

public class DoubleLinkedList {

    Node head = null;

    class Node {
        int data;
        Node prev, next;

        public Node(int d) {
            data = d;
            prev = next = null;
        }
    }

    public void insertBeginning(int new_data) {
        Node new_node = new Node(new_data);
        new_node.prev = null;
        new_node.next = head;
        if (head != null){
            head.prev = new_node;
        }
        head = new_node;
    }

    public void insertAfter(Node afterNode, int new_data) {
        if (head == null) {
            insertBeginning(new_data);
            return;
        }
        if (afterNode == null) {
            System.out.println("The given previous node cannot be null");
            return;
        }
        Node new_node = new Node(new_data);
        afterNode.next = new_node;
        new_node.prev = afterNode;
        new_node.next = afterNode.next;
        if (new_node.next != null) {
            afterNode.next.prev = new_node;
        }
    }

    public void insertAtSpecificPosition(int position, int new_data) {
        if (position == 0) {
            insertBeginning(new_data);
            return;
        }
        Node new_node = new Node(new_data);
        Node prev_node = head;
        int i=0;
        while (prev_node.next != null && i != position-1) {
            prev_node = prev_node.next;
            i++;
        }
        if (prev_node.next == null) {
            new_node.next = null;
            new_node.prev = prev_node;
            prev_node.next = new_node;
        } else {
            new_node.next = prev_node.next;
            new_node.prev = prev_node;
            prev_node.next.prev = new_node;
            prev_node.next = new_node;
        }
    }

    public void insertEnd(int new_data) {
        Node last_node = head;
        Node new_node = new Node(new_data);
        while (last_node.next != null) {
            last_node = last_node.next;
        }
        last_node.next = new_node;
        new_node.next = null;
        new_node.prev = last_node;
    }

    public void deleteBeginning() {
        head = head.next;
        head.prev.next = null;
        head.prev = null;
    }

    public void deleteEnd() {
        Node second_last_node = head;
        while (second_last_node.next.next != null) {
            second_last_node = second_last_node.next;
        }
        second_last_node.next.prev = null;
        second_last_node.next = null;
    }

    public void deleteAtSpecificPosition(int position) {
        Node del_node = head;
        int i = 1;
        if (position == 1) {
            deleteBeginning();
            return;
        }
        while (del_node != null && i != position) {
            del_node = del_node.next;
            i++;
        }
        if (del_node.next == null) {
            del_node.prev.next = null;
        } else {
            del_node.prev.next = del_node.next;
            del_node.next.prev = del_node.prev;
        }
        del_node.next = null;
        del_node.next = null;
    }

    public void printDoubleLinkedList(Node currNode) {
        Node prevNode = null;
        System.out.println("Double Linked List : ");
        while (currNode != null){
            System.out.print(currNode.data + "  ");
            prevNode = currNode;
            currNode = currNode.next;
        }
        System.out.println();
        printOppDoubleLinkedList(prevNode);
    }

    public void printOppDoubleLinkedList(Node lastNode) {
        System.out.println("Double Linked List (Opposite traversal): ");
        while (lastNode != null){
            System.out.print(lastNode.data + "  ");
            lastNode = lastNode.prev;
        }
        System.out.println();
    }
}
