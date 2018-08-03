package rifu.demo.sequencialMultiThread.service;

import rifu.demo.sequencialMultiThread.entity.Car;
import rifu.demo.sequencialMultiThread.enumeration.CarStatus;

public class WaxingMachine extends ServiceBase {

    public WaxingMachine(Shop services) {
        super(services);
    }

    @Override
    public void run() {
        while (isRunning) {
            Car carToBeServiced = shop.getNextServiceCar(CarStatus.WAX);
            try {
                if (carToBeServiced != null) {
                    shop.wax(carToBeServiced);
                    shop.waitNext(carToBeServiced);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void terminate() {
        this.isRunning = false;
    }
}
