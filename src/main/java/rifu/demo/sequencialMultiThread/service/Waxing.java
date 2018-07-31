package rifu.demo.sequencialMultiThread.service;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class Waxing implements Runnable {

    private AtomicInteger action;

    public Waxing(AtomicInteger action) {
        this.action = action;
    }

    @Override
    public void run() {
        synchronized (action) {
            for (; ; ) {
                if (action.intValue() != 1) {
                    try {
                        action.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else {
                    try {
                        System.out.println("Waxing start");
                        TimeUnit.SECONDS.sleep(1);
                        System.out.println("Waxing end\n");

                        action.decrementAndGet();

                        action.notify();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
