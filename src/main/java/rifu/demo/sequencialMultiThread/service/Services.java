package rifu.demo.sequencialMultiThread.service;

import rifu.demo.sequencialMultiThread.enumeration.CarStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

public class Services {

    private List<Car> carsToBeServiced = new ArrayList<>();

    public Services(List<Car> carsToBeServiced) {
        this.carsToBeServiced = carsToBeServiced;
    }

    public Car getNextServiceCar(CarStatus serviceStatus) {
        Optional<Car> optional =
                carsToBeServiced.stream().filter(o -> o.getStatus().equals(serviceStatus))
                        .findFirst();
        return optional.orElse(null);
    }

    public synchronized void wash(Car car) throws InterruptedException {
        System.out.print(car.getName() + " wash start");
        TimeUnit.SECONDS.sleep(5);
        CarStatus nextStatus = CarStatus.getNextStatus(car.getStatus());
        car.setStatus(nextStatus);
        System.out.println(" end");
        this.notifyAll();
    }

    public synchronized void wax(Car car) throws InterruptedException {
        System.out.print(car.getName() + " wax start");
        TimeUnit.SECONDS.sleep(1);
        CarStatus nextStatus = CarStatus.getNextStatus(car.getStatus());
        car.setStatus(nextStatus);
        System.out.println(" end");
        this.notifyAll();
    }

    public synchronized void waitNext(Car car) throws InterruptedException {
        System.out.println(car.getName() + " wait next");
        this.wait();
    }

    public void finish(Car car) {
        System.out.println(car.getName() + " finish");
        carsToBeServiced.remove(car);
    }
}
