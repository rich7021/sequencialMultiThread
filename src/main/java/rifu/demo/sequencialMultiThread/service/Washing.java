package rifu.demo.sequencialMultiThread.service;

import rifu.demo.sequencialMultiThread.Application;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

public class Washing implements Runnable {

    Object lock;

    public Washing(Object lock) {
        this.lock = lock;
    }

    @Override
    public void run() {
        synchronized (lock) {
            for (; ; ) {
                while (Application.action.intValue() != 0) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                if (Application.action.intValue() == 0) {
                    try {
                        System.out.println("Washing start");
                        TimeUnit.SECONDS.sleep(1);
                        System.out.println("Washing end");

                        Application.action.incrementAndGet();

                        lock.notify();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
