package org.enjekt.cipher.vernam.engine.internal.prng;


import java.util.concurrent.CyclicBarrier;

public class RNGRunnable implements Runnable {
    private final int number;
    private final IntQueue rngQueue;
    private final CyclicBarrier gate;

    public RNGRunnable(int number, IntQueue rngQueue, CyclicBarrier gate) {
        this.number = number;
        this.rngQueue = rngQueue;
        this.gate = gate;
    }

    @Override
    public void run() {
        try {
            gate.await();
        } catch (Exception e) {
        }

        while (true) {
            rngQueue.put(number);

        }
    }
}
