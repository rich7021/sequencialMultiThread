package rifu.demo.sequencialMultiThread.service;

import rifu.demo.sequencialMultiThread.entity.Car;
import rifu.demo.sequencialMultiThread.enumeration.CarStatus;

public class WashingMachine extends ServiceBase {

    public WashingMachine(Shop shop) {
        super(shop);
    }

    @Override
    public void run() {
        while (isRunning) {
            Car carToBeServiced = shop.getNextServiceCar(CarStatus.WASH);
            try {
                if (carToBeServiced != null) {
                    shop.wash(carToBeServiced);
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
