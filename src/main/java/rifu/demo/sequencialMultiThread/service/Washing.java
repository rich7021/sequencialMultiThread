package rifu.demo.sequencialMultiThread.service;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class Washing implements Runnable {

    public final static Integer ACTION_CODE = 0;

    private AtomicInteger action;
    private String message;

    public Washing(AtomicInteger action, String message) {
        this.action = action;
        this.message = message;
    }

    @Override
    public void run() {
        synchronized (action) {
            for (; ; ) {
                if (action.intValue() != ACTION_CODE) {
                    try {
                        action.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else if (action.intValue() == ACTION_CODE) {
                    try {
                        System.out.println(message + " start");
                        TimeUnit.SECONDS.sleep(1);
                        System.out.println(message + " end\n");

                        action.incrementAndGet();

                        action.notify();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else {
                    System.out.println("Unrecognized Action code");
                }
            }
        }
    }
}
