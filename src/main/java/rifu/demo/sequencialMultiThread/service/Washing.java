package rifu.demo.sequencialMultiThread.service;

import rifu.demo.sequencialMultiThread.enumeration.CarStatus;

public class Washing implements Runnable {

    private Services services;
    private boolean isRunning = true;

    public Washing(Services services) {
        this.services = services;
    }

    @Override
    public void run() {
        while (isRunning) {
            Car carToBeServiced = services.getNextServiceCar(CarStatus.WASH);
            try {
                if (carToBeServiced != null) {
                    services.wash(carToBeServiced);
                    services.waitNext(carToBeServiced);
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
