package rifu.demo.sequencialMultiThread;

import rifu.demo.sequencialMultiThread.service.Washing;
import rifu.demo.sequencialMultiThread.service.Waxing;

import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class Application {
    public static ExecutorService executorService = Executors.newCachedThreadPool();
    public static AtomicInteger action = new AtomicInteger(1);

    public static void main(String[] args) throws InterruptedException {

        Long start = System.currentTimeMillis();

        Object lock = new Object();

        Runnable waxing = new Waxing(lock);
        Runnable washing = new Washing(lock);
        executorService.execute(waxing);
        executorService.execute(washing);

        for (; ; ) {
            Map threads = Thread.getAllStackTraces();
            if (System.currentTimeMillis() - start > 180_000) {
                executorService.shutdownNow();
                break;
            }
        }

    }
}
