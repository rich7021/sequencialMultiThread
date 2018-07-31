package rifu.demo.sequencialMultiThread.service;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class Waxing implements Runnable {

    public final static Integer ACTION_CODE = 1;

    private AtomicInteger action;
    private String message;

    public Waxing(AtomicInteger action, String message) {
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

                        action.decrementAndGet();

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
