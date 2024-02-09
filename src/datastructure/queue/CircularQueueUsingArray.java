package datastructure.queue;

public class CircularQueueUsingArray {

    static class Queue {
        static int size;
        static int[] arr;
        static int front = -1;
        static int rear = -1;
        public Queue(int size) {
            this.arr = new int[size];
            this.size = size;
        }

        public static boolean isEmpty() {
            return rear == -1 && front == -1;
        }

        public static boolean isFull() {
            return (rear + 1) % size == front;
        }

        public static void add(int data) {
            if (isFull()) {
                System.out.println("Queue is Full");
                return;
            }
            //1st element add case
            if (front == -1) {
                front = 0;
            }
            rear = (rear + 1) % size;
            arr[rear] = data;
        }

        public static int remove() {
            if (isEmpty()) {
                System.out.println("Queue empty");
                return -1;
            }
            int top = arr[front];
            //single element condition
            if (rear == front) {
                rear = front = -1;
            } else {
                front = (front + 1) % size;
            }
            return top;
        }

        public static int peek() {
            if (isEmpty()) {
                System.out.println("Queue Empty");
                return rear;
            }
            return arr[front];
        }
    }

    public static void main(String[] args) {
        new Queue(5);
        Queue.add(1);
        Queue.add(2);
        Queue.add(3);
        Queue.add(4);
        Queue.add(5);
        System.out.println(Queue.remove());
        Queue.add(6);
        System.out.println(Queue.remove());
        Queue.add(7);
        while(!Queue.isEmpty()) {
            System.out.println(Queue.peek());
            Queue.remove();
        }
    }
}
