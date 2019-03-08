package datastructure.linkedlist.singlelinkedlist.insertdelete;

public class SingleLinkedList {

    Node head = null;

    class Node {
        int data;
        Node next;

        Node(int d) {
            data = d;
            next = null;
        }
    }

    public void insertBeginning(int new_data) {
        Node new_node = new Node(new_data);
        new_node.next = head;
        head = new_node;
    }

    public void insertAfter(Node prev_node, int new_data) {
        if (prev_node == null)
        {
            System.out.println("The given previous node cannot be null");
            return;
        }
        Node new_node = new Node(new_data);
        new_node.next = prev_node.next;
        prev_node.next = new_node;
    }

    public void insertAtNthPosition(int new_data, int position) {
        Node traverseNode = head;
        int count = 0;
        while (traverseNode != null){
            count++;
            traverseNode = traverseNode.next;
        }
        if(position <= 0) {
            insertBeginning(new_data);
        } else if (position >= count+1) {
            insertEnd(new_data);
        } else {
            traverseNode = head;
            count = 0;
            while (traverseNode != null){
                count++;
                if(count == position){
                    break;
                }
                traverseNode = traverseNode.next;
            }
            insertAfter(traverseNode, new_data);
        }
    }

    public void insertEnd(int new_data) {
        Node new_node = new Node(new_data);
        if(head == null) {
            head = new Node(new_data);
            return;
        }
        new_node.next = null;
        Node last_node = head;
        while (last_node.next != null){
            last_node = last_node.next;
        }
        last_node.next = new_node;
        return;
    }

    public void printList() {
        Node traverseNode = head;
        while (traverseNode != null){
            System.out.print(traverseNode.data + "  ");
            traverseNode = traverseNode.next;
        }
        System.out.println();
    }

    public void deleteBeginning() {
        if(head == null) {
            System.out.println("Nothing to delete; head is null");
            return;
        }
        if (head.next == null) {
            head = null;
            return;
        }
        Node del_node = head;
        head = head.next;
        del_node.next = null;
    }

    public void deleteEnd() {
        if(head == null) {
            System.out.println("Nothing to delete; head is null");
            return;
        }
        if (head.next == null) {
            head = null;
            return;
        }
        Node last_node = head;
        Node second_last_node = null;
        while (last_node.next != null){
            second_last_node = last_node;
            last_node = last_node.next;
        }
        second_last_node.next = null;
    }

    public void deleteSpecificData(int del_data) {
        Node currNode = head;
        if(currNode != null && currNode.data == del_data){
            head = head.next;
            currNode.next = null;
            return;
        }
        currNode = head;
        Node prevNode = null;
        while (currNode != null && currNode.data != del_data) {
            prevNode = currNode;
            currNode = currNode.next;
        }
        if (currNode == null) {
            System.out.println("Data not present in LL");
            return;
        }
        prevNode.next = currNode.next;
    }
}
