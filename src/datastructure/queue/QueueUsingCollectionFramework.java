package datastructure.queue;

import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;

public class QueueUsingCollectionFramework {

    public static void main(String[] args) {
        //using LinkedList
        Queue<Integer> q1 = new LinkedList<>();
        q1.add(1);
        q1.add(2);
        q1.add(3);
        q1.add(4);
        q1.add(5);
        while(!q1.isEmpty()) {
            System.out.println(q1.peek());
            q1.remove();
        }
        //using ArrayDeque
        Queue<Integer> q2 = new ArrayDeque<>();
        q2.add(1);
        q2.add(2);
        q2.add(3);
        q2.add(4);
        q2.add(5);
        while(!q2.isEmpty()) {
            System.out.println(q2.peek());
            q2.remove();
        }

    }
}
