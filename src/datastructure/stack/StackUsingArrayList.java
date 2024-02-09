package datastructure.stack;

import java.util.ArrayList;

public class StackUsingArrayList {

    static ArrayList<Integer> list = new ArrayList<>();

    static class Stack {
        public static boolean isEmpty() {
            return list.isEmpty();
        }

        public static void push(int data) {
            list.add(data);
        }

        public static Integer pop() {
            if (isEmpty())
                return -1;
            Integer top = list.get(list.size() - 1);
            list.remove(list.size() - 1);
            return top;
        }

        public static Integer peek() {
            if (isEmpty())
                return -1;
            return list.get(list.size() - 1);
        }
    }
    public static void main(String[] args) {
        Stack.push(1);
        Stack.push(2);
        Stack.push(3);
        Stack.push(4);

        while (!Stack.isEmpty()) {
            System.out.println(Stack.peek());
            Stack.pop();
        }
    }
}
