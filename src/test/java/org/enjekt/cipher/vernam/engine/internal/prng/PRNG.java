package org.enjekt.cipher.vernam.engine.internal.prng;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CyclicBarrier;

public class PRNG {
    List<Thread> threads = new ArrayList<Thread>();
    IntQueue queue = new IntQueue(100);

    public PRNG() {
        final CyclicBarrier gate = new CyclicBarrier(11);
//Get them all constructed and ready to go...
        for (int i = 0; i < 10; i++)
            threads.add(new Thread(new RNGRunnable(i, queue, gate)));
        //Then start them.
        for (int i = 0; i < 10; i++)
            threads.get(i).start();

        try {
            //Unblock all threads...
            gate.await();
        } catch (Exception e) {
        }
    }

    public int take() {
        return queue.take();
    }
}
