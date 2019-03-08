package datastructure.linkedlist.doublelinkedlist.insertdelete;

public class DoubleLinkedListMainApp {

    public static void main(String[] args) {
        System.out.println("==== Double Linked List =====");
        DoubleLinkedList dllist = new DoubleLinkedList();
        dllist.insertBeginning(6);
        dllist.insertBeginning(5);
        dllist.insertEnd(8);
        dllist.insertEnd(9);
        dllist.insertEnd(10);
        dllist.insertAtSpecificPosition(0, 4);
        dllist.insertAtSpecificPosition(3, 7);
        dllist.insertAtSpecificPosition(7, 11);
        dllist.deleteBeginning();
        dllist.deleteEnd();
        dllist.deleteAtSpecificPosition(1);
        dllist.deleteAtSpecificPosition(3);
        dllist.deleteAtSpecificPosition(4);
        dllist.printDoubleLinkedList(dllist.head);
        System.out.println("Thank You");
    }
}
