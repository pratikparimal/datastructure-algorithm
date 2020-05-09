package datastructure.stack;

public class StackUsingArray {

    static final int MAX = 100;
    int top;
    int[] arr = new int[MAX];

    public StackUsingArray() {
        top = -1;
    }

    public static void main(String[] args) {
        StackUsingArray stack = new StackUsingArray();
        stack.push(1);
        stack.push(2);
        stack.pop();
        stack.peek();
        stack.print();
    }

    private void print() {
        for (int i = 0; i <= top ; i++) {
            System.out.print(arr[i] + "  ");
        }
        System.out.println();
    }

    private int peek() {
        if (top < 0) {
            System.out.println("Stack Underflow");
            return -1;
        }
        return arr[top];
    }

    private int pop() {
        if (top < 0) {
            System.out.println("Stack Underflow");
            return -1;
        }
        return arr[top--];
    }

    private boolean push(int new_element) {
        if (top >= (MAX - 1)) {
            System.out.println("Stack Overflow");
            return false;
        }
        arr[++top] = new_element;
        System.out.println("Element pushed: " + new_element);
        return true;
    }
}
