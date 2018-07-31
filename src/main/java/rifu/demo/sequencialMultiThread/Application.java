package rifu.demo.sequencialMultiThread;

import rifu.demo.sequencialMultiThread.service.Washing;
import rifu.demo.sequencialMultiThread.service.Waxing;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class Application {
    public static ExecutorService executorService = Executors.newCachedThreadPool();
    public static AtomicInteger action = new AtomicInteger(1);

    public static void main(String[] args) throws InterruptedException {

        Long start = System.currentTimeMillis();

        Waxing waxing = new Waxing();
        Washing washing = new Washing();
        Set<Runnable> todo = new HashSet();
        todo.add(washing, waxing);
        executorService.invokeAll(todo);

        for (; ; ) {
            if (action.intValue() == 1) {
                washing.wait();
                waxing.notify();
            } else {
                waxing.wait();
                washing.notify();
            }

            if (System.currentTimeMillis() - start > 180_000) {
                executorService.shutdownNow();
                break;
            }
        }

    }
}
