package rifu.demo.sequencialMultiThread.service;

import rifu.demo.sequencialMultiThread.Application;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

public class Waxing implements Runnable {

    Object lock;

    public Waxing(Object lock) {
        this.lock = lock;
    }

    @Override
    public void run() {
        synchronized (lock) {
            for (; ; ) {
                while (Application.action.intValue() != 1) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                if (Application.action.intValue() == 1) {
                    try {
                        System.out.println("Waxing start");
                        TimeUnit.SECONDS.sleep(1);
                        System.out.println("Waxing end");

                        Application.action.decrementAndGet();

                        lock.notify();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
