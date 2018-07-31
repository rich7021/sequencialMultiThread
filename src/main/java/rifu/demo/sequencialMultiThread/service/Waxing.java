package rifu.demo.sequencialMultiThread.service;

import rifu.demo.sequencialMultiThread.Application;

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
