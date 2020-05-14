package datastructure.stack;

public class StackUsingLinkedList {

    static class StackNode {
        int data;
        StackNode next;

        StackNode(int d) {
            data = d;
            next = null;
        }
    }

    static StackNode top = null;

    public static void main(String[] args) {
        StackUsingLinkedList stack = new StackUsingLinkedList();
        stack.push(10);
        stack.push(20);
        stack.push(30);
        stack.pop();
        stack.push(40);
        stack.peek();
        stack.print();
        stack.pop();
        stack.peek();
        stack.print();
    }

    private void push(int new_element) {
        StackNode new_node = new StackNode(new_element);
        if (top == null) {
            top = new_node;
        } else {
            StackNode temp = top;
            top = new_node;
            new_node.next = temp;
        }
        System.out.println("Element pushed: " + new_element);
    }

    private void pop() {
        if (top == null) {
            System.out.println("Stack empty; Underflow Condition");
        } else {
            System.out.println("Element popped: " + top.data);
            top = top.next;
        }
    }

    private void peek() {
        if (top == null) {
            System.out.println("Stack empty; Underflow Condition");
        } else {
            System.out.println("Topmost Element: " + top.data);
        }
    }

    private void print() {
        if (top == null) {
            System.out.println("Stack empty");
        } else {
            System.out.print("Elements in Stack: ");
            StackNode node = top;
            while (node != null) {
                System.out.print(node.data + "  ");
                node = node.next;
            }
        }
        System.out.println();
    }
}
