package ridu.demo.sequencialMultiThread.service;

import ridu.demo.sequencialMultiThread.Application;

public class Waxing implements Runnable {

    @Override
    public void run() {
        for (; ; ) {
            try {
                System.out.println("Waxing start");
                Thread.sleep(3000);
                System.out.println("Waxing end");

                Application.action.decrementAndGet();

            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
