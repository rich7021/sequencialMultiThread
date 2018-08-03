package rifu.demo.sequencialMultiThread;

import rifu.demo.sequencialMultiThread.entity.Car;
import rifu.demo.sequencialMultiThread.service.*;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Application {
    public static int CAR_AMOUNT = 1;
    public static ExecutorService EXECUTOR_SERVICE = Executors.newFixedThreadPool(2);

    public static void main(String[] args) throws InterruptedException {

        List<Car> cars = new CopyOnWriteArrayList<>();
        for (int i = 0; i < CAR_AMOUNT; i++) {
            cars.add(new Car("car" + i, null));
        }

        Shop shop = new Shop(cars);
        List<ServiceBase> services =
                Arrays.asList(new WaxingMachine(shop), new WashingMachine(shop));
        services.forEach(o -> EXECUTOR_SERVICE.execute(o));

        EXECUTOR_SERVICE.awaitTermination(10, TimeUnit.SECONDS);
        services.forEach(o -> o.terminate());
        EXECUTOR_SERVICE.shutdown();

        cars.forEach(car -> System.out.println(car.getName() + " " + car.getStatus()));

    }
}
