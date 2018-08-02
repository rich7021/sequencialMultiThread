package rifu.demo.sequencialMultiThread;

import rifu.demo.sequencialMultiThread.service.Car;
import rifu.demo.sequencialMultiThread.service.Services;
import rifu.demo.sequencialMultiThread.service.Washing;
import rifu.demo.sequencialMultiThread.service.Waxing;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Application {
    public static int CAR_AMOUNT = 1;
    public static ExecutorService EXECUTOR_SERVICE = Executors.newCachedThreadPool();

    public static void main(String[] args) throws InterruptedException {

        List<Car> cars = new CopyOnWriteArrayList<>();
        for (int i = 0; i < CAR_AMOUNT; i++) {
            cars.add(new Car("car" + i, null));
        }

        Collections.synchronizedCollection(cars);

        Services services = new Services(cars);

        Runnable waxing = new Waxing(services);
        Runnable washing = new Washing(services);
        EXECUTOR_SERVICE.execute(waxing);
        EXECUTOR_SERVICE.execute(washing);


        EXECUTOR_SERVICE.awaitTermination(30, TimeUnit.SECONDS);


        ((Washing) washing).terminate();
        ((Waxing) waxing).terminate();

        EXECUTOR_SERVICE.shutdown();

        cars.forEach(car -> System.out.println(car.getName() + " " + car.getStatus()));

    }
}
