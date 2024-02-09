package datastructure.queue.queueUsing2Stacks;

//Microsoft, Google asked question

import java.util.Stack;

/**
 * Explanation:
 * Adding 4 elements to Queue
 * 1, 2, 3, 4
 * We have 2 stacks s1 and s2
 * - push(1) to s1
 * - before pushing another no. to s1, pop everything from s1 and push it to s2
 *   i.e. pop(1) from s1 -> push(1) to s2. At this point, s1 is empty and s2 have 1
 * - push(2) to s1
 * - now pop everything from s2 and push to s1. At this point, s1 has 2 -> 1 and s2 is empty
 * - now again pop everything from s1 and push it to s2. At this point, s1 is empty and s2 has 1 -> 2
 * - push(3) to s1
 * - now pop everything from s2 and push to s1. At this point, s1 has 3 -> 2 -> 1 and s2 is empty
 * - now again pop everything from s1 and push it to s2. At this point, s1 is empty and s2 has 1 -> 2 -> 3
 * - push(4) to s1
 * - now pop everything from s2 and push to s1. At this point, s1 has 4 -> 3 -> 2 -> 1 and s2 is empty
 * - now again pop everything from s1 and push it to s2. At this point, s1 is empty and s2 has 1 -> 2 -> 3 -> 4
 * - now pop everything from s2 and push to s1. At this point, s1 has 4 -> 3 -> 2 -> 1 and s2 is empty
 */


public class QueueUsing2Stacks {

    static class Queue {
        static Stack<Integer> s1 = new Stack<>();
        static Stack<Integer> s2 = new Stack<>();

        public static boolean isEmpty() {
            return s1.isEmpty() && s2.isEmpty();
        }

        public static void add(int data) {
            while (!s1.isEmpty()) {
                s2.push(s1.pop());
            }
            s1.push(data);
            while (!s2.isEmpty()) {
                s1.push(s2.pop());
            }
        }

        public static int remove() {
            if (isEmpty()) {
                System.out.println("Queue Empty");
                return -1;
            }
            return s1.pop();
        }

        public static int peek() {
            if (isEmpty()) {
                System.out.println("Queue Empty");
                return -1;
            }
            return s1.peek();
        }
    }

    public static void main(String[] args) {
        Queue.add(1);
        Queue.add(2);
        Queue.add(3);
        Queue.add(4);
        Queue.add(5);

        while(!Queue.isEmpty()) {
            System.out.println(Queue.peek());
            Queue.remove();
        }
    }
}
