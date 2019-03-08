package datastructure.linkedlist.singlelinkedlist.insertdelete;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SingleLinkedListUtility {

    public void insertOrDelete() throws IOException {
        SingleLinkedList llist = new SingleLinkedList();
        int choice = 0, data;
        do {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("What would you like to do ?\n1.Insert at beginning\n2.Insert at end\n3.Insert after nth node\n" +
                    "4.Delete from beginning\n5.Delete from end\n6.Delete specific data");
            int c = Integer.parseInt(br.readLine());
            switch (c) {
                case 1:
                    System.out.println("Enter data");
                    data = Integer.parseInt(br.readLine());
                    llist.insertBeginning(data);
                    break;
                case 2:
                    System.out.println("Enter data");
                    data = Integer.parseInt(br.readLine());
                    llist.insertEnd(data);
                    break;
                case 3:
                    System.out.println("Enter data");
                    data = Integer.parseInt(br.readLine());
                    System.out.println("After which node u want to insert ?");
                    int position = Integer.parseInt(br.readLine());
                    llist.insertAtNthPosition(data, position);
                    break;
                case 4:
                    llist.deleteBeginning();
                    break;
                case 5:
                    llist.deleteEnd();
                    break;
                case 6:
                    System.out.println("Enter data to be deleted");
                    data = Integer.parseInt(br.readLine());
                    llist.deleteSpecificData(data);
                    break;
                default:
                    System.out.println("Possible options : Insertion or Deletion only");
                    return;
            }
            System.out.println("Linked List :");
            llist.printList();
            System.out.println("Do you wish to continue ? (1/0)");
            choice = Integer.parseInt(br.readLine());
        } while (choice == 1);
    }
}
