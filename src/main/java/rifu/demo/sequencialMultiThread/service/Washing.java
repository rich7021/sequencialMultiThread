package rifu.demo.sequencialMultiThread.service;

import rifu.demo.sequencialMultiThread.Application;

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
                }

                if (action.intValue() == 0) {
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
