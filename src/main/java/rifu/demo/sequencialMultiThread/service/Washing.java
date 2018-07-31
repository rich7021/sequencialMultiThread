package rifu.demo.sequencialMultiThread.service;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class Washing implements Runnable {

    private AtomicInteger action;

    public Washing(AtomicInteger action) {
        this.action = action;
    }

    @Override
    public void run() {
        synchronized (action) {
            for (; ; ) {
                if (action.intValue() != 0) {
                    try {
                        action.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else {
                    try {
                        System.out.println("Washing start");
                        TimeUnit.SECONDS.sleep(1);
                        System.out.println("Washing end\n");

                        action.incrementAndGet();

                        action.notify();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
