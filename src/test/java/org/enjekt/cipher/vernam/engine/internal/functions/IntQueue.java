package org.enjekt.cipher.vernam.engine.internal.functions;

public class IntQueue {

    private final int[] queue;
    private int counter = 0;

    public IntQueue(int size) {
        queue = new int[size];
    }

    public void put(int e) {
        synchronized (queue) {

            if (counter < queue.length - 1) {
                queue[++counter] = e;
                queue.notifyAll();
            } else {
                try {
                    queue.wait();
                } catch (InterruptedException ex) {
                }
            }
        }

    }

    public int take() {
        synchronized (queue) {
            while (counter < 0)
                try {
                    queue.wait();
                } catch (InterruptedException ex) {
                }


            int val = queue[counter--];
            queue.notifyAll();
            return val;
        }
    }
}