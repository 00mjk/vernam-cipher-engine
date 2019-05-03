package org.enjekt.cipher.vernam.engine.internal.prng;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CyclicBarrier;

public class PRNG {
    List<Thread> threads = new ArrayList<Thread>();
    IntQueue queue = new IntQueue(100);

    public PRNG() {
        final CyclicBarrier gate = new CyclicBarrier(11);

        for (int i = 0; i < 10; i++) {
            Thread t = new Thread(new RNGRunnable(i, queue, gate));
            t.start();
            threads.add(t);
        }

        try {
            //Start all threads...
            gate.await();
        } catch (Exception e) {
        }
    }

    public int take() {
        return queue.take();
    }
}
