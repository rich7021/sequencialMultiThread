package rifu.demo.sequencialMultiThread;

import rifu.demo.sequencialMultiThread.service.Washing;
import rifu.demo.sequencialMultiThread.service.Waxing;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class Application {
    public static ExecutorService EXECUTOR_SERVICE = Executors.newCachedThreadPool();
    public static AtomicInteger CURRENT_ACTION = new AtomicInteger(1);

    public static void main(String[] args) throws InterruptedException {

        Long start = System.currentTimeMillis();

        Runnable waxing = new Waxing(CURRENT_ACTION, "Waxing");
        Runnable washing = new Washing(CURRENT_ACTION, "Washing");
        EXECUTOR_SERVICE.execute(waxing);
        EXECUTOR_SERVICE.execute(washing);

        for (; ; ) {
            if (System.currentTimeMillis() - start > 180_000) {
                EXECUTOR_SERVICE.shutdownNow();
                break;
            }
        }

    }
}
