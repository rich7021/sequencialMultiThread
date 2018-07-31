package ridu.demo.sequencialMultiThread.service;

import ridu.demo.sequencialMultiThread.Application;

import java.util.concurrent.TimeUnit;

public class Washing implements Runnable {

    @Override
    public void run() {
        for (; ; ) {

            try {
                System.out.println("Washing start");
                TimeUnit.SECONDS.sleep(3);
                System.out.println("Washing end");

                Application.action.incrementAndGet();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
